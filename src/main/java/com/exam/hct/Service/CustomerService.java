package com.exam.hct.Service;

import com.exam.hct.Entity.*;

import java.util.List;

public interface CustomerService {

   public CustomerDetAddress saveCustomer(CustomerDetAddress customerDetAddress);

  public List<CustomerDetails> getCustomer();

  public String createTransaction(NewTransaction transaction);

   public List<Transaction> getByAccountId(AccountBalance accountBalance);

    AccountBalance getBalanceById(long customerId);

    AccountBalance getBalance(long accountId);

    List<Transaction> getByRefId(Long refId);
}
