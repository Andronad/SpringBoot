package com.iba.courses.service;

import com.ibm.ims.connect.*;
import org.springframework.stereotype.Service;

import java.net.SocketException;

@Service
public class IMSService {
    private TmInteraction tmInteraction;
    private ConnectionFactory connectionFactory;
    private Connection connection;

    public void init() throws Exception {
        try {
            this.connectionFactory = new ConnectionFactory();
            this.connectionFactory.setHostName("172.20.2.116");
            this.connectionFactory.setPortNumber(7003);
            this.connectionFactory.setSocketType(ConnectionFactory.SOCKET_TYPE_PERSISTENT);
            this.connection = this.connectionFactory.getConnection();
            this.tmInteraction = this.connection.createInteraction();

            /* Interaction settings */
            this.tmInteraction.setImsConnectTimeout(5000);
            this.tmInteraction.setInteractionTimeout(50000);
            this.tmInteraction.setTrancode("");
            this.tmInteraction.setInputMessageDataSegmentsIncludeLlzzAndTrancode(false);
            this.tmInteraction.setCommitMode(ApiProperties.COMMIT_MODE_0);
            this.tmInteraction.setResponseIncludesLlll(true);
            this.tmInteraction.setInteractionTypeDescription(ApiProperties.INTERACTION_TYPE_DESC_SENDRECV);
            this.tmInteraction.setImsConnectUserMessageExitIdentifier("*SAMPL1*");
            this.tmInteraction.setImsDatastoreName("IVP1");
            /* RACF settings */
            this.tmInteraction.setRacfUserId("LAPUSHA");
            this.tmInteraction.setRacfPassword("LAPUSHA2");
            this.tmInteraction.setRacfGroupName("");
            this.tmInteraction.setRacfApplName("");
        } catch (Exception e) {
            System.out.println("Error in initializing");
            e.printStackTrace();
        }
    }
    public void connect(){
        try {
            this.connection.connect();
        } catch (Exception e) {
            System.out.println("Error in connecting");
            e.printStackTrace();
        }
    }
    public String execute(String command){
        try {
            InputMessage inputMessage = this.tmInteraction.getInputMessage();
            inputMessage.setInputMessageData(command.getBytes(tmInteraction.getImsConnectCodepage()));
            this.tmInteraction.execute();
            OutputMessage outputMessage = tmInteraction.getOutputMessage();
            return outputMessage.getDataAsString();
        } catch (Exception e) {
            System.out.println("Error in executing");
            e.printStackTrace();
        }
        return "Error";
    }

}
