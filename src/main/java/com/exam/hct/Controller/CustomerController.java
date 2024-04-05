package com.exam.hct.Controller;

import com.exam.hct.Entity.*;
import com.exam.hct.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/post")
    public ResponseEntity<CustomerDetAddress> addCustomer(@RequestBody CustomerDetAddress customerDetAddress){
        return new ResponseEntity<>(customerService.saveCustomer(customerDetAddress), HttpStatus.CREATED);
    }
@GetMapping("/customers")
    public List<CustomerDetails> getCustomer(){
        return customerService.getCustomer();
}
@PostMapping("/transaction")
    public String createTransaction(@RequestBody NewTransaction transaction){
        String type=transaction.getType();
        if(type.equals("credit")){
            return customerService.createTransaction(transaction);
        }
        else {
            return "error";
        }
}
@GetMapping("/transaction/{id}")
    public  List<Transaction> getByAccountId(@PathVariable("id")AccountBalance accountBalance){
        return customerService.getByAccountId(accountBalance);
}
@GetMapping("/{customerId}")
    public ResponseEntity<AccountBalance> getBalanceById(@PathVariable("customerId") long customerId){
    AccountBalance balance =  customerService.getBalanceById(customerId);

    if (balance != null) {
        return new ResponseEntity<>(balance, HttpStatus.OK);
    } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); //
    }
}
@GetMapping("/balance/{id}")
    public AccountBalance getBalance(@PathVariable("id") long accountId){
        return customerService.getBalance(accountId);
}
    @GetMapping("/ref/{refId}")
    public List<Transaction> getByRefId(@PathVariable("refId") Long refId){
        return customerService.getByRefId(refId);
    }
    @GetMapping("/refAcc/{refId}/{accId}")
    public List<Transaction> getByRefAcc(@PathVariable("refId") Long transRefId, @PathVariable("accId")AccountBalance accountBalance){
        if(accountBalance==null){
            return customerService.getByRefId(transRefId);
        }
        else return customerService.getByAccountId(accountBalance);
    }


}
