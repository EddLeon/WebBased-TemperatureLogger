// Import required libraries
#include <DHT.h>
#include "ESP8266WiFi.h"

//Inicializar el sensor
#define DHTPIN 2 //Se conecta al pin 2 del modulo
#define DHTTYPE DHT11

// WiFi parameters
const char* ssid = "itesm"; //Red
const char* password = NULL; //contraseña de la red
const char* host = "10.15.82.226"; //direccion IP del servidor

DHT dht(DHTPIN, DHTTYPE, 15);

void setup(void)
{ 
  // Start Serial
  Serial.begin(115200);
  delay(10);

  //Se inicia el sensor
  dht.begin();
  
  // Connect to WiFi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("");
  Serial.println("WiFi connected");
  // Print the IP address
  Serial.println(WiFi.localIP());
}


void loop() {
  int t = (int)dht.readTemperature();
  Serial.println(t);

  WiFiClient client;
  const int httpPort = 8084;
  
  if(!client.connect(host, httpPort)){
    Serial.println("connection failed");
    return;
  }

  //Se envia el valor al servidor
  client.print(String("GET /ProyectoJava/dataBridge?temperature=") + String(t) + " HTTP/1.1\r\n" +
  "Host: " + host + "\r\n" + 
  "Connection: close\r\n\r\n");
  delay(10);

  while(client.available()){
    String line = client.readStringUntil('\r');
    Serial.print(line);
  }

  Serial.println();
  Serial.println("Closing connection");

  delay(10000);
  
}



