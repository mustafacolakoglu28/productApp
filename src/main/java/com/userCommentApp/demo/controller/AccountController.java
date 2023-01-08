package com.userCommentApp.demo.controller;

import com.userCommentApp.demo.controller.request.account.AddAccountRequest;
import com.userCommentApp.demo.controller.request.account.DeleteByEmailAndPhoneNumberRequest;
import com.userCommentApp.demo.controller.request.account.FindByPhoneNumberRequest;
import com.userCommentApp.demo.controller.request.account.UpdateAccountRequest;
import com.userCommentApp.demo.controller.response.account.AddAccountResponse;
import com.userCommentApp.demo.controller.response.account.DeleteAccountResponse;
import com.userCommentApp.demo.controller.response.account.GetAccountsResponse;
import com.userCommentApp.demo.controller.response.account.UpdateAccountResponse;
import com.userCommentApp.demo.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AddAccountResponse> addAccount(@RequestBody AddAccountRequest request){
        return ResponseEntity.ok(AddAccountResponse.toResponse(accountService.addAccount(request)));

    }

    @GetMapping
    public ResponseEntity <List<GetAccountsResponse>> getAllAccounts(){
        return ResponseEntity.ok(GetAccountsResponse.toResponse(accountService.getAllAccounts()));
    }


    @GetMapping("findbyname")
    public ResponseEntity<List<GetAccountsResponse>> getAccountsByName(
             @RequestParam String name){
        return ResponseEntity.ok(GetAccountsResponse.toResponse(accountService.getAccountsByName(name)));
    }

    @GetMapping("findbyphonenumber")
    public ResponseEntity <List<GetAccountsResponse>> getAccountsByPhoneNumber(
            @Valid @ModelAttribute FindByPhoneNumberRequest request) {
        return ResponseEntity.ok(GetAccountsResponse
                .toResponse(accountService
                        .getAccountsByPhoneNumber(request)));
    }

    @DeleteMapping
    public ResponseEntity<DeleteAccountResponse> deleteAccountByPhoneNumberAndEmail(
            @Valid @ModelAttribute DeleteByEmailAndPhoneNumberRequest request){
       return ResponseEntity.ok(DeleteAccountResponse
               .toResponse(accountService
                       .deleteAccountByPhoneNumberAndEmail(request)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateAccountResponse> updateAccount(@PathVariable("id") String id,
                                                              @Valid @RequestBody UpdateAccountRequest request){
        return ResponseEntity.ok(UpdateAccountResponse.toResponse(accountService.updateAccount(id, request)));
    }
}
