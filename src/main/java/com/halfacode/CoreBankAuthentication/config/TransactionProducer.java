package com.halfacode.CoreBankAuthentication.config;

import com.halfacode.CoreBankAuthentication.entity.Transaction;

import org.springframework.stereotype.Component;

//@Component
public class TransactionProducer {
    private static final String TOPIC = "transaction-topic"; // Replace with your desired topic name

   /* @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;
*/
    public void sendTransaction(Transaction transaction) {
    //    kafkaTemplate.send(TOPIC, transaction);
    }
}