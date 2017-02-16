
import java.io.File;
import java.io.IOException;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Track;
import javax.swing.JFileChooser;

/**
 * 
 * @author math
 * 
 */
public class GarageBandPlayer {
	/**
	 * shift of all played notes in half-notes
	 */
	private int shift;
	/**
	 * dynamics of playing sounds
	 */
	private int dynamics;
	/**
	 * MidiChannel played by synthesizer
	 */
	private MidiChannel channel;
	/**
	 * Synthesizer which plays all sounds
	 */
	private Synthesizer synthesizer;
	/**
	 * MidiMessage receiver, which collects all messages to record
	 */
	private Receiver recReceiver;
	/**
	 * Recording flag, 1 if recording, 0 if not
	 */
	private boolean recording;
	/**
	 * Tab of currently playing notes
	 */
	private boolean tab[];
	/**
	 * Recording sequencer
	 */
	private Sequencer seq;
	/**
	 * Time of the beginning of recording
	 */
	private long time;
	/**
	 * Current Instrument
	 */
	private Instrument instrument;
	/**
	 * List of all instruments available
	 */
	private Instrument tab2[];
	/**
	 * File to load song from
	 */
	private File file;
	/**
	 * Recording sequence
	 */
	private Sequence sequence;
	/**
	 * Recording track
	 */
	private Track tmp;
	/**
	 * Recording sequencer
	 */
	private Sequencer sequencer;

	GarageBandPlayer() {
		tab = new boolean[127];
		try {
			synthesizer = MidiSystem.getSynthesizer();
			synthesizer.open();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MidiChannel[] midiChannels = synthesizer.getChannels();
		channel = midiChannels[0];
		synthesizer.loadAllInstruments(synthesizer.getDefaultSoundbank());
		tab2 = synthesizer.getLoadedInstruments();

		for (int i = 0; i < tab2.length; i++) {
			System.out.println(i + " " + tab2[i].getName());
		}

		instrument = tab2[0];
		System.out.println(instrument.getName());

		channel.programChange(instrument.getPatch().getBank(), instrument.getPatch().getProgram());// 25
																									// guitar

		shift = 0;/////
		dynamics = 60;
		System.out.println("Latency: " + synthesizer.getLatency() / 1000 + "ms.");
		recording = false;
	}

	/**
	 * Starts playing note, and if recording, sends messages to recorder. Shift
	 * and Dynamics can be set in functions setShift() and setDynamics()
	 * 
	 * @param note
	 *            - note velocity, 60 is middle C if there is no shift.
	 */
	protected void playNote(char note) {
		if (!tab[note]) {
			ShortMessage myMsg = new ShortMessage();
			Receiver myRcvr = null;
			try {
				myRcvr = synthesizer.getReceiver();
			} catch (MidiUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Play the note Middle C (60) moderately loud
			// (velocity = 93)on channel 4 (zero-based).
			try {
				myMsg.setMessage(ShortMessage.NOTE_ON, 0, note + shift, dynamics);
			} catch (InvalidMidiDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myRcvr.send(myMsg, -1); // -1 means no time stamp
			if (recording) {
				System.out.println("Timer_microsec: " + (System.nanoTime() / 1000 - time));
				recReceiver.send(myMsg, System.nanoTime() / 1000 - time);
			}
		}
		tab[note] = true;
	}

	/**
	 * Stops playing note, and if recording, sends messages to recorder.
	 * 
	 * @param note
	 *            - note velocity, 60 is middle C if there is no shift.
	 */
	protected void playNoteStop(char note) {
		ShortMessage myMsg = new ShortMessage();
		Receiver myRcvr = null;
		try {
			myRcvr = synthesizer.getReceiver();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		try {
			myMsg.setMessage(ShortMessage.NOTE_OFF, 0, note + shift, dynamics);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}

		myRcvr.send(myMsg, -1); // -1 means no time stamp
		if (recording)
			recReceiver.send(myMsg, System.nanoTime() / 1000 - time);
		tab[note] = false;
	}

	/**
	 * Changes instrument to parameter
	 * 
	 * @param instrument
	 *            - number of instrument in instrument bank
	 */
	protected void setInstrument(char instr) {
		instrument = tab2[instr];
		channel.programChange(instrument.getPatch().getBank(), instrument.getPatch().getProgram());
	}

	protected void setShift(int x) {
		shift = x;
	}

	/**
	 * Starts recording to seq/sequence
	 */
	protected void startRec() {
		recording = true;
		try {
			time = System.nanoTime() / 1000;
			System.out.println("Time: " + time);
			seq = MidiSystem.getSequencer();
			seq.open();
			sequence = new Sequence(Sequence.SMPTE_30, 20); // Type of timing
															// and time
															// resolution
			sequence.createTrack();
			seq.setSequence(sequence);
			tmp = sequence.getTracks()[0];
			seq.recordEnable(tmp, -1); // all channels from tmp to seq
			seq.startRecording();
		} catch (MidiUnavailableException | InvalidMidiDataException e) {
			e.printStackTrace();
		}
		try {
			recReceiver = seq.getReceiver();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ShortMessage myMsg = new ShortMessage();
		try {
			myMsg.setMessage(ShortMessage.PROGRAM_CHANGE, 0, instrument.getPatch().getProgram(),
					instrument.getPatch().getBank());
		} catch (InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		recReceiver.send(myMsg, 0);
	}

	/**
	 * Stops recording and saves recorded song in midi file
	 */
	void stopRec() {
		if (!recording)
			return;
		seq.stop();
		seq.start();
		recording = false;
		File myMidiFile = new File("Record.mid");
		int tab[] = MidiSystem.getMidiFileTypes(sequence);
		try {
			System.out.println("Powinno dojsc");
			MidiSystem.write(sequence, tab[0], myMidiFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void setDynamics(char dynamics) {
		this.dynamics = dynamics;
	}

	/**
	 * Loads and starts a song from file (if song is already running, it starts
	 * from the beginning)
	 * 
	 * @param param
	 *            - temporary parameter 0 if came from file browser button, 1 if
	 *            came from play button
	 */

	public void startSong(int param) {
		if (sequencer != null && sequencer.isRunning()) {
			sequencer.setMicrosecondPosition(0);
			sequencer.start();
			return;
		}

		if (param == 0) {

			JFileChooser browser = new JFileChooser("./Songs");
			file = null;

			if (browser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { //

				file = browser.getSelectedFile();

				try {
					sequencer = MidiSystem.getSequencer();
					sequencer.open();
					if (sequencer.isRunning())
						return;

				} catch (MidiUnavailableException e1) {
					e1.printStackTrace();
				}

				try {
					File myMidiFile = file;
					Sequence mySeq = MidiSystem.getSequence(myMidiFile);
					sequencer.setSequence(mySeq);
				} catch (Exception e) {
					e.printStackTrace();
				}
				sequencer.start();
				paused = false;
				pauseTime = 0;
			} //

		}

		else if (file != null) { 
			try {
				sequencer = MidiSystem.getSequencer();
				sequencer.open();
				if (sequencer.isRunning())
					return;

			} catch (MidiUnavailableException e1) {
				e1.printStackTrace();
			}

			try {
				File myMidiFile = file;
				Sequence mySeq = MidiSystem.getSequence(myMidiFile);
				sequencer.setSequence(mySeq);
			} catch (Exception e) {
				e.printStackTrace();
			}
			sequencer.start();
			paused = false;
			pauseTime = 0;
		}

	}

	/**
	 * Stops song
	 */
	public void stopSong() {
		if (sequencer != null)
			sequencer.stop();
		paused = false;
		pauseTime = 0;
	}

	private long pauseTime;
	private boolean paused;

	/**
	 * Pauses song if running, starts if paused
	 */
	public void pauseSong() {
		if (!sequencer.isRunning() && !paused)
			return;
		if (!paused) {
			pauseTime = sequencer.getMicrosecondPosition();
			sequencer.stop();
			paused = true;
		} else {
			sequencer.setMicrosecondPosition(pauseTime);
			sequencer.start();
			paused = false;
		}
	}

	private long pauseTime2;
	private boolean paused2;

	/**
	 * Pauses recorded song if running, starts if paused
	 */
	public void pauseRecordedSong() {
		if (!seq.isRunning())
			return;
		if (!paused2) {
			pauseTime2 = seq.getMicrosecondPosition();
			seq.stop();
			paused2 = true;
		} else {
			seq.setMicrosecondPosition(pauseTime2);
			seq.start();
			paused2 = false;
		}
	}
}