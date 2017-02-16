
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;


public class GarageBandController implements KeyListener {

	private GarageBandView view;
	private GarageBandPlayer player;
	Thread watek;

	public GarageBandController(GarageBandView view, GarageBandPlayer player) {
		this.view = view;
		this.player = player;
		view.registerListener(this);
		this.view.registerPlayer(player);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		char a = MIDIKey(e.getKeyChar());
		if (a != 0)
			player.playNote(a);
		else {// 16,17,18 - shift, control, alt
			System.out.println(e.getKeyCode());
			switch (e.getKeyCode()) {
			case 16:
				System.out.println("Dynamics 127 :D");
				player.setDynamics((char)127);
				break;
			case 17:
				System.out.println("Dynamic 80 :|");
				player.setDynamics((char)80);
				break;
				
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		char a = MIDIKey(e.getKeyChar());
		if (a != 0)
			player.playNoteStop(a);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
	private char MIDIKey(char key) {
		switch (key) {
		case 'q':
			return 40;
		case '2':
			return 41;
		case 'w':
			return 42;
		case '3':
			return 43;
		case 'e':
			return 44;
		case 'r':
			return 45;
		case '5':
			return 44;
		case 't':
			return 47;
		case '6':
			return 48;
		case 'y':
			return 49;
		case '7':
			return 50;
		case 'u':
			return 51;
		case 'i':
			return 52;
		case '9':
			return 53;
		case 'o':
			return 54;
		case '0':
			return 55;
		case 'p':
			return 56;
		case '[':
			return 55;
		case '=':
			return 58;
		case ']':
			return 59;

		case 'z':
			return 72;
		case 's':
			return 73;
		case 'x':
			return 74;
		case 'd':
			return 75;
		case 'c':
			return 76;
		case 'v':
			return 77;
		case 'g':
			return 78;
		case 'b':
			return 79;
		case 'h':
			return 80;
		case 'n':
			return 81;
		case 'j':
			return 82;
		case 'm':
			return 83;
		case ',':
			return 84;
		case 'l':
			return 85;
		case '.':
			return 86;
		case ';':
			return 87;
		case '/':
			return 88;
		}
		return 0;
	}
	
	/**
	 * 
	 * @param nazwa
	 * @param lista
	 * starting a new thread with teaching module if no other is working  
	 */
	
	void naukaGry(String nazwa, ArrayList <JButton> lista,ArrayList <JButton> lista2,ArrayList <JButton> lista3,char instrument){	
		if(watek==null){
			watek = new Thread(new Teacher(nazwa,lista,lista2,lista3,instrument));
			watek.start();
		}
	}

	/**
	 * closing teaching thread after pushing the stop button
	 */
	
	void koniecNauki(){
		
		if(watek!=null){
			watek.interrupt();
			watek.stop();
			watek=null;
		}
		
	}
	
}
