import eu.larkc.csparql.cep.api.RdfQuadruple;
import eu.larkc.csparql.cep.api.RdfStream;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class humidityStreamGenerator extends RdfStream implements Runnable {
    
    protected final Logger logger = LoggerFactory.getLogger(humidityStreamGenerator.class);	
    private int c = 1;
    private boolean keepRunning = false;
     
    private RdfQuadruple q=null;
     
    int generatedHumidity=0;
    long generatedTime=0L;
  
      
    public humidityStreamGenerator(final String iri){
        super(iri);
    }
    
    public void pleaseStop() {
	keepRunning = false;
    }
    
    @Override
    public void run() {
	keepRunning = true;
        Random rnd = new Random();
       
        while (keepRunning) {
            //Instant instant=Instant.now();
            generatedTime=System.currentTimeMillis();
          generatedHumidity= 40 + (int)(Math.random()*(50 - 40));
           
            q = new RdfQuadruple("http://localhost:8080/smartSpace#humitidyReadings" + this.c,
			"http://localhost:8080/smartSpace#hasHumidityReading", 
                        //"http://www.semanticweb.org/40011133/ontologies/2017/10/untitled-ontology-21#temperatureValue" + this.c,
                        String.valueOf(generatedHumidity) ,
                        generatedTime);
                this.put(q);
            try {
                Thread.sleep(1*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.c++; 
            
        }
    }
}

