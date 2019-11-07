# ING Bank Java Client
The library allows to login to ING bank system and fetch accounts and also transactions history.

For educational purposes, it's not recommended to use this in productions environments.
 

## Example code
```java
 Configuration configuration = Configuration.builder()
                .url("https://login.ingbank.pl/mojeing/rest/")
                .login("<username>") // user login
                .password("<password>") // user password
                .build();

IngConnector ingConnector = new IngConnector(configuration);
EasyIng easyIng = new EasyIng(ingConnector);
AccountsResponseData accounts = easyIng.getAccounts();
        System.out.println("cur: ");
accounts.getAccts().getCur().getAccts().forEach(acc -> {
    System.out.println(String.format("%s | %s", acc.getName(), acc.getAcct()));
    System.out.println("Transactions: ");
    HistoryResponseData transactions = null;
    try {
        transactions = easyIng.getTransactions(Collections.singletonList(acc.getAcct()), "", "2019-11-07", "2019-11-07", 10);
    } catch (IngException e) {
        throw new RuntimeException(e);
    }
    transactions.getTrns().forEach(w -> {
        HistoryResponseData.Transaction m = w.getM();
        System.out.println(String.format("%s | %s | %s | %s", m.getT1(),m.getM1(), m.getAmt(), m.getDate()));
    });
});

System.out.println("sav: ");
accounts.getAccts().getSav().getAccts().forEach(acc -> {
    System.out.println(String.format("%s | %s", acc.getName(), acc.getAcct()));
    HistoryResponseData transactions = null;
    try {
        transactions = easyIng.getTransactions(Collections.singletonList(acc.getAcct()), "", "2019-11-07", "2019-11-07", 10);
    } catch (IngException e) {
        throw new RuntimeException(e);
    }
    transactions.getTrns().forEach(w -> {
        HistoryResponseData.Transaction m = w.getM();
        System.out.println(String.format("%s | %s | %s | %s", m.getT1(),m.getM1(), m.getAmt(), m.getDate()));
    });
});
```