#include <dht11.h>
//#########################################################################################################
dht11 sensorUmidadeTemperatura;
//#########################################################################################################
#define ILUMINACAO              10   // define que a iluminacao será ativada / desativado na porta 10
#define UMIDIFICADOR            11   // define que o alarme de incendio será ativado/desativado pela porta 11
#define AQUECED0R               12   // Define que o aquecedor será ativado/desativado pela porta 12
#define AR_CONDICIONADO         13   // Define que o ar condicionado será ativado/desativado pela porta 13
#define PIN_SENSOR_UMID_TEMP     2   // Define que o sensor de temperatura estar enviando dados atravéz da porta 2
#define PIN_SENSOR_LUMINOSIDADE A0   // Define que o sensor de luminosidade estará conectado ao pino A0
//##########################################################################################################



int dado; //variavel que recebera dados na porta serial
long intervaloLeituraIluminacao = 10000;
long valorUltimaLeituraIluminacao = 0;
long intervaloEscritaNaPorta = 1000;
long valorUltimaEscritaNaPorta = 0;
float vlrLuminosidade;
String json = "";
byte a, b, c , d;

void setup() {
  Serial.begin(9600); // frequencia da porta serial
  pinMode(ILUMINACAO, OUTPUT); // define o pino p ledPin como saida
  pinMode(UMIDIFICADOR, OUTPUT); // define o pino p ledPin como saida
  pinMode(AQUECED0R, OUTPUT); // define o pino p ledPin como saida
  pinMode(AR_CONDICIONADO, OUTPUT); // define o pino p ledPin como saida
  vlrLuminosidade = (float) analogRead(PIN_SENSOR_LUMINOSIDADE) / 10;
}

void loop() {
  lerComandosRecebidos();

  //GERA UMA STRING NO FORMATO JSON
  // if ((millis() % 1000) == 0) {
  if ((millis() - valorUltimaEscritaNaPorta) >= intervaloEscritaNaPorta) {


    /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
       @     LÊ OS DADOS VINDOS DOS PINOS                @
     /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
    // Imprime dados da temperatura
    Serial.print("{temperatura: ");
    Serial.print(temperatura());
    Serial.print(", ");
    // imprime dados da umidade
    Serial.print("umidade: ");
    Serial.print(umidade());
    Serial.print(", ");
    // imprime dados da luminosidade
    Serial.print("luminosidade: ");
    Serial.print(luminosidade());
    Serial.print(", ");
    // imprime dados do id dos dispositivo
    Serial.print("identificador: ");
    Serial.print("AMB-01");
    Serial.print(", ");
    /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
      @     ATIVA / DESTATIVA SENSORES NO ARDUINO       @
    /*@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*/
    // imprime o estado do alarme de magnetismo
    Serial.print("statusIluminacao: ");
    Serial.print(digitalRead(ILUMINACAO));
    Serial.print(", ");
    // imprime o estado do alarme de incendio
    Serial.print("statusAquecedor: ");
    Serial.print(digitalRead(AQUECED0R));
    Serial.print(", ");
    // imprime o estado do aquecedor
    Serial.print("statusArcondicionado: ");
    Serial.print(digitalRead(AR_CONDICIONADO));
    Serial.print(", ");
    // imprime o estado do ar condicionado
    Serial.print("statusUmidificador: ");
    Serial.print(digitalRead(UMIDIFICADOR));
    Serial.print(", ");
    // imprime o status geral, caso haja algum esquipamento de saida liga imprime 1 caso todos estejam desligado 0
    Serial.print("statusGeral: ");
    Serial.print(statusGeral(digitalRead(ILUMINACAO), digitalRead(AQUECED0R), digitalRead(AR_CONDICIONADO), digitalRead(UMIDIFICADOR)));
    // Serial.print(", ");
    Serial.println("}SENSOR#");
    Serial.println("*");
    valorUltimaEscritaNaPorta = millis();
    Serial.flush();
  }





}


byte statusGeral(byte a, byte b, byte c, byte d) {
  if (a == 1 || b == 1 || c == 1 || d == 1) {
    return 1;
  } else {
    return 0;
  }
}


void lerComandosRecebidos() {
  if (Serial.available() > 0) { //verifica se existe comunicação com a porta serial
    dado = Serial.read();

    switch (dado) {

      //LIGA / DESLIGA ALARME_MAGNETISMO
      case 100:
        digitalWrite(ILUMINACAO, LOW);// desliga o pino
        break;
      case 101:
        digitalWrite(ILUMINACAO, HIGH);//liga o pino
        break;
      //LIGA / DESLIGA ALARME_INCENCIO
      case 110:
        digitalWrite(UMIDIFICADOR, LOW);// desliga o pino
        break;
      case 111:
        digitalWrite(UMIDIFICADOR, HIGH);//liga o pino
        break;
      //LIGA / DESLIGA AQUECED0R
      case 120:
        digitalWrite(AQUECED0R, LOW);// desliga o pino
        break;
      case 121:
        digitalWrite(AQUECED0R, HIGH);//liga o pino
        break;
      // LIGA / DESLIGA AR_CONDICIONADO
      case 130:
        digitalWrite(AR_CONDICIONADO, LOW);// desliga o pino
        //Serial.println("Desligando");
        break;
      case 131:
        digitalWrite(AR_CONDICIONADO, HIGH);//liga o pino
        //Serial.println("Ligando");
        break;
    }

  }

}
/*#################################################################################################
 *###################### VERIFICA SE O SENSOR ESTÁ RESPONDENDO ####################################
 *###############################################################################################*/
boolean verificarStatusSensor() {
  int resposta = sensorUmidadeTemperatura.read(PIN_SENSOR_UMID_TEMP);
  switch (resposta) {
    case DHTLIB_OK:
      // Serial.println("OK");
      return true;
    case DHTLIB_ERROR_CHECKSUM:
      //Serial.println("Erro no checksum");
      return false;
    case DHTLIB_ERROR_TIMEOUT:
      // Serial.println("Tempo esgotado");
      return false;
    default:
      //  Serial.println("Erro desconhecido");
      return false;
  }
}
/*#################################################################################################
 *###################### RETORNA UMIDADE CASO O SENSOR ESTEJA ATIVO ###############################
 *###############################################################################################*/
float umidade() {
  if (verificarStatusSensor()) {
    return sensorUmidadeTemperatura.humidity;
  } else {
    return 0.00;
  }
}
/*#################################################################################################
 *###################### RETORNA A TEMPERATURA CASO O SENSOR ESTEJA ATIVO #########################
 *###############################################################################################*/
float temperatura() {
  if (verificarStatusSensor()) {
    return sensorUmidadeTemperatura.temperature;
  } else {
    return 0.00;
  }
}
/*#################################################################################################
 *###################### RETORNA O VALOR LIDO DA PORTA A0 #########################################
 *###############################################################################################*/
float luminosidade() {
  if ((millis() - valorUltimaLeituraIluminacao) >= intervaloLeituraIluminacao) {
    vlrLuminosidade =  (float) analogRead(PIN_SENSOR_LUMINOSIDADE) / 10;
    valorUltimaLeituraIluminacao = millis();
  }
  return vlrLuminosidade;
}

