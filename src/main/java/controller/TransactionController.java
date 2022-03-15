package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import service.TransactionService;

@RequestMapping("/transaction")
@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Transaction> createTransaction(@RequestBody Transaction t){
        return transactionService.createTransaction(t);
    }

    @GetMapping(value = "/get/account/{idAccount}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Flux<Transaction> findByAccountId(@PathVariable("idAccount") Integer id){
        return transactionService.findAllByAccountId(id);
    }

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Mono<Transaction>> findById(@PathVariable("id") Integer id){
        Mono<Transaction> transactionMono = transactionService.findByTransactionId(id);
        return new ResponseEntity<Mono<Transaction>>(transactionMono, transactionMono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<Transaction> updateTransaction(@RequestBody Transaction t){
        return transactionService.updateTransaction(t);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable("id") Integer id){
        return transactionService.deleteTransaction(id);
    }

}
