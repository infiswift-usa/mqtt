package mqtt.Mqtt_Application;

import java.util.concurrent.TimeUnit;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReceiveFromMqttBroker {
    public void receiveFromMqttBroker() throws InterruptedException, JSONException {
        
        String topic        = "/infiswift.tech/stream/version/1/id/+/name/inverter";
         
        int qos             = 1;
        
        String broker       = "tcp://preview-mqtt-broker.infiswift.tech:1883";
        
        String clientId     = "396bdea0d7cd46d580b5eef7fe7504ad";

        MemoryPersistence persistence = new MemoryPersistence();

        try {
        
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            char[] password = {'3','d','0','d','a','d','a','3','-','f','9','7','a','-','4','f','0','5','-','8','1','4','d','-','a','c','5','b','5','3','c','8','0','e','4','f'};
           
            connOpts.setCleanSession(true);
            connOpts.setConnectionTimeout(30 * 1000);
            connOpts.setKeepAliveInterval(0);
            connOpts.setAutomaticReconnect(true);
            connOpts.setUserName("jSNms8O");
            connOpts.setPassword(password);
          
            connOpts.setMqttVersion(4);
            System.out.println("Connecting to broker: "+broker);
            
            sampleClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {}

                public void messageArrived(String topic, MqttMessage message) throws Exception {
                  System.out.println("Message: " + message.toString());
                }

                public void deliveryComplete(IMqttDeliveryToken token) {}
              });
            
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Subscribing message");
    
            System.out.println(topic);
            sampleClient.subscribe(topic,qos);
            System.out.println("Message subscribed");
            TimeUnit.SECONDS.sleep(3);
            sampleClient.disconnect();
            System.out.println("Disconnected");
           // System.exit(0);
        	
        } catch(MqttException me) {
            System.out.println("reason " + me.getReasonCode());
            System.out.println("msg " + me.getMessage());
            System.out.println("loc " + me.getLocalizedMessage());
            System.out.println("cause " + me.getCause());
            System.out.println("excep " + me);
            me.printStackTrace();
        }
    	
    }
}
