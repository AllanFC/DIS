import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;
public class SimpleMqttCallBack implements MqttCallback {
    int status = 0;
    MqttClient sampleClient;
    boolean turnedON = false;

    public SimpleMqttCallBack(MqttClient sampleClient) {
        this.sampleClient = sampleClient;
    }

    public void connectionLost(Throwable throwable) {
        System.out.println("Connection to MQTT broker lost!");
    }

    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String res= new String(mqttMessage.getPayload());
        // res indeholder en mÂling som et JSON-object
        // put real stuff here     < --------    !!!!!!!!!!
        JSONObject jo = new JSONObject(res);
        JSONObject jo1 = jo.getJSONObject("AM2301");
        double temp = jo1.getDouble("Temperature");
        double humidity = jo1.getDouble("Humidity");
        System.out.printf("Temperature: %s\nHumidity: %s\n", temp,humidity);

        if(humidity > 35) {
            MQTTprogram.publishMessage(sampleClient, "cmnd/grp7685/Power1", "1");
            turnedON = true;
        } else if (humidity < 35 && turnedON) {
            MQTTprogram.publishMessage(sampleClient, "cmnd/grp7685/Power1", "0");
            turnedON = false;
        }
    }

    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        // not used in this example
    }
} 