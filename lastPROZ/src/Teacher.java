import java.util.ArrayList;
import javax.swing.JButton;

public class Teacher implements Runnable{
	
	/**
	 * teaching module; works as an individual thread 
	 */
	
	private String nazwa;
	ArrayList<JButton> lista;
	ArrayList<JButton> lista2;
	ArrayList <JButton> lista3;
	char instrument;

	
	/**
	 * 
	 * @param nazwa
	 * @param lista
	 * we need to color the button if it must be clicked to generate proper sound
	 * we need to know which song must be played
	 */
	
	Teacher(String nazwa,ArrayList <JButton>lista,ArrayList <JButton>lista2,ArrayList <JButton>lista3,char instrument){
		
		this.nazwa = nazwa;
		this.lista = lista;	//gitara
		this.lista2 = lista2;	//pianino
		this.lista3 = lista3;	//bas
		this.instrument = instrument;
	}
	
	/**
	 * 
	 * @param indeks
	 * switching off buttons
	 */
	
	void zgas(int indeks,ArrayList<JButton>lista){
		
        lista.get(indeks).setOpaque(false);
        lista.get(indeks).setContentAreaFilled(false);
        //lista.get(indeks).setBorderPainted(false);
		
	}
	
	/**
	 * 
	 * @param indeks
	 * @param mili
	 * switching on button's label for 'mili' seconds
	 */
		
	void zapal(int indeks,int mili,ArrayList<JButton> lista){
		
        lista.get(indeks).setOpaque(true);
        lista.get(indeks).setContentAreaFilled(true);
        lista.get(indeks).setBorderPainted(true);
        try {
			Thread.sleep(mili);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        zgas(indeks,lista);

	}
	
	/**
	 * smoke on the water - deep purple
	 */
	
	void dymNaWodzie(){
		
		GarageBandPlayer plejer= new GarageBandPlayer();
        plejer.setInstrument(instrument);	               
        if(instrument == 25){
        while(true){ 
	    
        	try	{
        		
      	         plejer.playNote((char)60);
      	         zapal(8,450,lista);
      	         plejer.playNoteStop((char)60);
      	          
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)63);
      	         zapal(11,450,lista);
      	         plejer.playNoteStop((char)63);
      	         
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)65);
      	         zapal(1,800,lista);
      	         plejer.playNoteStop((char)65);	
      	         
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)60);
      	         zapal(8,450,lista);
      	         plejer.playNoteStop((char)60);
      	         
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)63);
      	         zapal(11,450,lista);
      	         plejer.playNoteStop((char)63);
      	         
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)66);
      	         zapal(2,400,lista);
      	         plejer.playNoteStop((char)66);
      	         
      	         plejer.playNote((char)65);
      	         zapal(1,600,lista);
      	         plejer.playNoteStop((char)65);
      	         
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)60);
      	         zapal(8,450,lista);
      	         plejer.playNoteStop((char)60);
      	         
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)63);
      	         zapal(11,450,lista);
      	         plejer.playNoteStop((char)63);
      	         
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)65);
      	         zapal(1,800,lista);
      	         plejer.playNoteStop((char)65);	//
      	         
      	         Thread.sleep(200);

      	         plejer.playNote((char)63);
      	         zapal(11,450,lista);
      	         plejer.playNoteStop((char)63);
      	         
      	         Thread.sleep(150);
      	         
      	         plejer.playNote((char)60);
      	         zapal(8,600,lista);
      	         plejer.playNoteStop((char)60);
      	         Thread.sleep(200);
      	         }
	    
	    
        	catch(Exception e)	{e.printStackTrace();}
	    
	    
        	finally{
        		
        		for(int i=0;i<100;i++)	plejer.playNoteStop((char)i);
        		
        		
        		for(JButton przycisk:lista){
                    przycisk.setOpaque(false);
                    przycisk.setContentAreaFilled(false);
                    //przycisk.setBorderPainted(false);
                }

        	}
	    
	               }
        }
        else{
        	
            while(true){ 
        	    
            	try	{
    	            		
    	         plejer.playNote((char)36);
    	         zapal(16,450,lista3);
    	         plejer.playNoteStop((char)36);
    	          
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)39);
    	         zapal(7,450,lista3);
    	         plejer.playNoteStop((char)39);
    	         
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)41);
    	         zapal(9,800,lista3);
    	         plejer.playNoteStop((char)41);	
    	         
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)36);
    	         zapal(16,450,lista3);
    	         plejer.playNoteStop((char)36);
    	         
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)39);
    	         zapal(7,450,lista3);
    	         plejer.playNoteStop((char)39);
    	         
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)42);
    	         zapal(10,400,lista3);
    	         plejer.playNoteStop((char)42);
    	         
    	         plejer.playNote((char)41);
    	         zapal(9,600,lista3);
    	         plejer.playNoteStop((char)41);
    	         
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)36);
    	         zapal(16,450,lista3);
    	         plejer.playNoteStop((char)36);
    	         
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)39);
    	         zapal(7,450,lista3);
    	         plejer.playNoteStop((char)39);
    	         
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)41);
    	         zapal(9,800,lista3);
    	         plejer.playNoteStop((char)41);	//
    	         
    	         Thread.sleep(200);

    	         plejer.playNote((char)39);
    	         zapal(7,450,lista3);
    	         plejer.playNoteStop((char)39);
    	         
    	         Thread.sleep(150);
    	         
    	         plejer.playNote((char)36);
    	         zapal(16,600,lista3);
    	         plejer.playNoteStop((char)36);
    	         Thread.sleep(200);
    	         }
    	    
    	    
            	catch(Exception e)	{e.printStackTrace();}
    	    
    	    
            	finally{
            		
            		for(int i=0;i<100;i++)	plejer.playNoteStop((char)i);
            		
            		
            		for(JButton przycisk:lista3){
                        przycisk.setOpaque(false);
                        przycisk.setContentAreaFilled(false);
                        //przycisk.setBorderPainted(false);
                    }

            	}
    	    
    	               }
            }

        	
        	
        }
        
	           
	
	
	/**
	 * breaking the law - judas priest
	 */
	
	void lamaniePrawa(){
		
		GarageBandPlayer plejer= new GarageBandPlayer();
        plejer.setInstrument((char)30);	    
        
        while(true){
        
        try{
        	
	         plejer.playNote((char)60);
	         zapal(0,200,lista);
	         plejer.playNoteStop((char)60);
	          
	         Thread.sleep(100);
	         
	         plejer.playNote((char)62);
	         zapal(12,300,lista);
	         plejer.playNoteStop((char)62);
	          
	         Thread.sleep(100);
	         
	         plejer.playNote((char)63);
	         zapal(18,300,lista);
	         plejer.playNoteStop((char)63);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)60);
	         zapal(0,200,lista);
	         plejer.playNoteStop((char)60);
	          
	         Thread.sleep(100);
	         
	         plejer.playNote((char)62);
	         zapal(12,300,lista);
	         plejer.playNoteStop((char)62);
	          
	         Thread.sleep(100);
	         
	         plejer.playNote((char)63);
	         zapal(18,300,lista);
	         plejer.playNoteStop((char)63);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)60);
	         zapal(0,200,lista);
	         plejer.playNoteStop((char)60);
	          
	         Thread.sleep(100);
	         
	         plejer.playNote((char)62);
	         zapal(12,300,lista);
	         plejer.playNoteStop((char)62);
	          
	         Thread.sleep(100);
	         
	         plejer.playNote((char)63);
	         zapal(18,300,lista);
	         plejer.playNoteStop((char)63);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)56);
	         zapal(7,300,lista);
	         plejer.playNoteStop((char)56);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)58);
	         zapal(19,300,lista);
	         plejer.playNoteStop((char)58);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)60);
	         zapal(0,300,lista);
	         plejer.playNoteStop((char)60);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)56);
	         zapal(7,300,lista);
	         plejer.playNoteStop((char)56);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)58);
	         zapal(19,300,lista);
	         plejer.playNoteStop((char)58);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)60);
	         zapal(0,300,lista);
	         plejer.playNoteStop((char)60);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)58);
	         zapal(19,300,lista);
	         plejer.playNoteStop((char)58);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)63);
	         zapal(18,300,lista);
	         plejer.playNoteStop((char)63);
	          
	         Thread.sleep(50);
	         
	         plejer.playNote((char)62);
	         zapal(12,300,lista);
	         plejer.playNoteStop((char)62);
	          
	         Thread.sleep(50);
	         
	         
        	
        	
        }
        
    	catch(Exception e)	{e.printStackTrace();}
	    
	    
    	finally{
    		
    		for(int i=0;i<100;i++)	plejer.playNoteStop((char)i);
    		
    		
    		for(JButton przycisk:lista){
                przycisk.setOpaque(false);
                przycisk.setContentAreaFilled(false);
                //przycisk.setBorderPainted(false);
            }
    	}
        
        }
        
	}
        
	/**
	 * nemo - nightwish
	 */
	
	void nemo(){
		
		GarageBandPlayer plejer= new GarageBandPlayer();
        plejer.setInstrument((char)0);	       
        
        while(true){
        
        try{
        	
        	Thread.sleep(395);
        	
        	plejer.playNote((char)74);
        	zapal(11,395,lista2);
	        plejer.playNoteStop((char)74);
        	
        	plejer.playNote((char)74);
        	zapal(11,197,lista2);
	        plejer.playNoteStop((char)74);
	        
        	plejer.playNote((char)72);
        	zapal(10,197,lista2);
	        plejer.playNoteStop((char)72);
	        
        	plejer.playNote((char)74);
        	zapal(11,197,lista2);
	        plejer.playNoteStop((char)74);
	        
        	plejer.playNote((char)69);
        	zapal(8,197,lista2);
	        plejer.playNoteStop((char)69);
	        
        	Thread.sleep(395);
        	
        	plejer.playNote((char)74);
        	zapal(11,395,lista2);
	        plejer.playNoteStop((char)74);
        	
        	plejer.playNote((char)74);
        	zapal(11,197,lista2);
	        plejer.playNoteStop((char)74);
	        
        	plejer.playNote((char)72);
        	zapal(10,197,lista2);
	        plejer.playNoteStop((char)72);
	        
        	plejer.playNote((char)74);
        	zapal(11,197,lista2);
	        plejer.playNoteStop((char)74);
	        
        	plejer.playNote((char)65);
        	zapal(6,197,lista2);
	        plejer.playNoteStop((char)65);
	        
        	plejer.playNote((char)65);
        	zapal(6,395,lista2);
	        plejer.playNoteStop((char)65);
	        
	        
        	plejer.playNote((char)72);
        	zapal(10,395,lista2);
	        plejer.playNoteStop((char)72);
	        
        	
        	plejer.playNote((char)72);
        	zapal(10,197,lista2);
	        plejer.playNoteStop((char)72);
	        
        	plejer.playNote((char)74);
        	zapal(11,197,lista2);
	        plejer.playNoteStop((char)74);
	        
	        
        	plejer.playNote((char)69);
        	zapal(8,197,lista2);
	        plejer.playNoteStop((char)69);
	        
        	plejer.playNote((char)67);
        	zapal(7,197,lista2);
	        plejer.playNoteStop((char)67);
	        
	        //
	        Thread.sleep(197);
        	plejer.playNote((char)65);
        	zapal(6,197,lista2);
	        plejer.playNoteStop((char)65);
	        
	        Thread.sleep(197);
        	plejer.playNote((char)64);
        	zapal(5,197,lista2);
	        plejer.playNoteStop((char)64);
	        
	        Thread.sleep(197);
        	plejer.playNote((char)60);
        	zapal(13,197,lista2);
	        plejer.playNoteStop((char)60);
	        
	        Thread.sleep(197);
        	plejer.playNote((char)62);
        	zapal(0,197,lista2);
	        plejer.playNoteStop((char)62);
	          
        }
        
    	catch(Exception e)	{e.printStackTrace();}
	    
	    
    	finally{
    		
    		for(int i=0;i<100;i++)	plejer.playNoteStop((char)i);
    		
    		
    		for(JButton przycisk:lista2){
                przycisk.setOpaque(false);
                przycisk.setContentAreaFilled(false);
               // przycisk.setBorderPainted(false);
            }
    	}
        
        }
        
	}

	/**
	 * method which must be implemented in case we want to set Teacher object to be an executable thread 
	 * simple switch to choose which song must be played
	 */
	
	public void run() {
		
		switch(nazwa){
		case("Smoke on the water"):dymNaWodzie(); break;
		case("Breaking the law"):lamaniePrawa(); break;
		case("Nemo"):nemo(); break;
		}

	
	}

}
