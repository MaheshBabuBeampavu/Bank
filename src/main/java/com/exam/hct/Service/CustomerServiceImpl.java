package com.exam.hct.Service;


import com.exam.hct.Controller.GenerateRandom;
import com.exam.hct.Entity.*;
import com.exam.hct.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service


public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDetailsRepository customerDetailsRepository;
    @Autowired
    AccountBalanceRepository accountBalanceRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    CustomerMapRepository customerMapRepository;
    @Autowired
    TransactionRepository transactionRepository;



//    @Override
//    public CustomerDetAddress saveCustomer(CustomerDetAddress customerDetAddress) {
//        GenerateRandom gm=new GenerateRandom();
//        Timestamp timestamp=new Timestamp(new Date().getTime());
//      CustomerAddress customerDetAddress1=new CustomerAddress();
//      long aid= gm.getRandom();
//      customerDetAddress1.setAddress_Id(aid);
//
//        customerDetAddress1.setAddressLane(customerDetAddress.getAddressLane());
//        customerDetAddress1.setCity(customerDetAddress.getCity());
//        customerDetAddress1.setPin(customerDetAddress.getPin());
//        customerDetAddress1.setCountry(customerDetAddress.getCountry());
////       customerDetAddress1.set
//
//
//
//
//   CustomerDetails customerDetails=new CustomerDetails();
//   long customerId= gm.getRandom();
//   customerDetails.setCustomerId(customerId);
//             customerDetails.setCustomerName(customerDetAddress.getCustomerName());
//        customerDetails.setPhone(customerDetAddress.getPhone());
//             customerDetails.setEmail(customerDetAddress.getEmail());
//             customerDetails.setCreated(timestamp);
//             customerDetails.setLastUpdated(timestamp);
//        AccountBalance accountBalance=new AccountBalance();
//        long accId=gm.getRandom();
//        accountBalance.setAccountId(accId);
//        accountBalance.setBalance(500.0);
////             customerDetails.setAddress(customerDetAddress1);
////
//            CustomerAddress customerAddress =addressRepository.save(customerDetAddress1);
//            customerDetails.setAddress(customerAddress);
//         CustomerDetails savedCustomerDetails1=customerDetailsRepository.save(customerDetails);
//         AccountBalance savedAccount=accountBalanceRepository.save(accountBalance);
//
//
//
//
//        CustomerAccountMap customerAccountMap=new CustomerAccountMap();
//        long a = gm.getRandom();
//        customerAccountMap.setId(a);
//       customerAccountMap.setCustomerDetails(customerDetails);
//       customerAccountMap.setAccountBalance(accountBalance);
//       customerMapRepository.save(customerAccountMap);
//       return customerDetAddress;
//    }
@Override
public CustomerDetAddress saveCustomer(CustomerDetAddress customerDetAddress) {
    Timestamp timestamp = new Timestamp(new Date().getTime());
    CustomerAddress customerDetAddress1 = new CustomerAddress();
    GenerateRandom gm = new GenerateRandom();
    long aid = gm.getRandom();
    customerDetAddress1.setAddress_Id(aid);
    customerDetAddress1.setAddressLane(customerDetAddress.getAddressLane());
    customerDetAddress1.setCity(customerDetAddress.getCity());
    customerDetAddress1.setPin(customerDetAddress.getPin());
    customerDetAddress1.setCountry(customerDetAddress.getCountry());

    CustomerDetails customerDetails = new CustomerDetails();
    customerDetails.setCustomerName(customerDetAddress.getCustomerName());
    customerDetails.setPhone(customerDetAddress.getPhone());
    customerDetails.setEmail(customerDetAddress.getEmail());
    customerDetails.setCreated(timestamp);
    customerDetails.setLastUpdated(timestamp);

    // Save the CustomerDetails to generate a unique customerId
    CustomerDetails savedCustomerDetails = customerDetailsRepository.save(customerDetails);

    AccountBalance accountBalance = new AccountBalance();
    long accId = gm.getRandom();
    accountBalance.setAccountId(accId);
    accountBalance.setBalance(500.0);

    CustomerAddress customerAddress = addressRepository.save(customerDetAddress1);
    savedCustomerDetails.setAddress(customerAddress);

    CustomerAccountMap customerAccountMap = new CustomerAccountMap();
    long a = gm.getRandom();
    customerAccountMap.setId(a);
    customerAccountMap.setCustomerDetails(savedCustomerDetails);
    customerAccountMap.setAccountBalance(accountBalance);
    customerMapRepository.save(customerAccountMap);

    return customerDetAddress;
}


    @Override
    public List<CustomerDetails> getCustomer() {
        return customerDetailsRepository.findAll();
    }

    @Override
    public String createTransaction(NewTransaction transaction) {
        GenerateRandom gm = new GenerateRandom();
        Timestamp timestamp=new Timestamp(new Date().getTime());
        long accountId= transaction.getAccountId();
        long toAccountId= transaction.getToAccountId();
//        long transactionIdSender=gm.getRandom();
//        long transactionIdReceiver=gm.getRandom();
        long referenceId=gm.getRandom();
        double amount=transaction.getAmount();


        Transaction transaction1=new Transaction();
//        transaction1.setTransactionId(transactionIdSender);
        transaction1.setTransactionRefId(referenceId);
       transaction1.setDebit(amount);
       transaction1.setCredit(0.0);
       transaction1.setLastUpdated(timestamp);


       Transaction transaction2=new Transaction();
//        transaction2.setTransactionId(transactionIdReceiver);
       transaction2.setTransactionRefId(referenceId);
       transaction2.setCredit(amount);
       transaction2.setDebit(0.0);
       transaction2.setLastUpdated(timestamp);
     Optional<AccountBalance> OReceiver = accountBalanceRepository.findById(accountId);
     if(OReceiver.isPresent()){
         AccountBalance sender=OReceiver.get();
         double balance= sender.getBalance();
         if(balance>=amount){
             sender.setBalance(balance-amount);
             transaction1.setAvailableBalance(sender.getBalance());
             AccountBalance abs=accountBalanceRepository.save(sender);
             transaction1.setAccountBalance(abs);

         }
         else return "less balance";

     }
     Optional<AccountBalance>reciever=accountBalanceRepository.findById(toAccountId);
        if(reciever.isPresent()){
            AccountBalance rec=reciever.get();
            double balancee= rec.getBalance();
            if(balancee>=amount){
                rec.setBalance(balancee+amount);
                transaction2.setAvailableBalance(rec.getBalance());
                AccountBalance abs=accountBalanceRepository.save(rec);
                transaction2.setAccountBalance(abs);
                transactionRepository.save(transaction1);
                transactionRepository.save(transaction2);

            }
            else return "less balance";

        }

        return "successfull";
    }

    @Override
    public List<Transaction> getByAccountId(AccountBalance accountBalance) {
         List<Transaction> transactionList=new ArrayList<>();
         List<Transaction> list=transactionRepository.findByAccountBalance(accountBalance);
         for(Transaction abv : list){
             Transaction transaction=new Transaction();
             transaction.setTransactionId(abv.getTransactionId());
             transaction.setAccountBalance(abv.getAccountBalance());
             transaction.setDebit(abv.getDebit());
             transaction.setCredit(abv.getCredit());
             transaction.setLastUpdated(abv.getLastUpdated());
             transaction.setAvailableBalance(abv.getAvailableBalance());
             transaction.setLastUpdated(abv.getLastUpdated());
             transactionList.add(transaction);
         }
         return transactionList;
    }

    @Override
    public AccountBalance getBalanceById(long customerId) {
  


        CustomerDetails customerDetails = customerDetailsRepository.findById(customerId).orElse(null);
        CustomerAccountMap customerAccountMap = customerDetails.getCustomerAccountMap();
        AccountBalance abc = customerAccountMap.getAccountBalance();
        return abc;


    }

    @Override
    public AccountBalance getBalance(long accountId) {
       AccountBalance accountBalance=accountBalanceRepository.findById(accountId).get();
       return accountBalance;
    }

    @Override
    public List<Transaction> getByRefId(Long refId) {
        List<Transaction> transactionList=new ArrayList<>();
        List<Transaction> list=transactionRepository.findByTransactionRefId(refId);
        for(Transaction abv : list){
            Transaction transaction=new Transaction();
            transaction.setTransactionId(abv.getTransactionId());
            transaction.setAccountBalance(abv.getAccountBalance());
            transaction.setDebit(abv.getDebit());
            transaction.setCredit(abv.getCredit());
            transaction.setLastUpdated(abv.getLastUpdated());
            transaction.setAvailableBalance(abv.getAvailableBalance());
            transaction.setLastUpdated(abv.getLastUpdated());
            transactionList.add(transaction);
        }
        return transactionList;

    }
}
