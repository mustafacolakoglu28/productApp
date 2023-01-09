package com.userCommentApp.demo.service;

import com.userCommentApp.demo.controller.request.comment.AddCommentRequest;
import com.userCommentApp.demo.dto.AccountCommentDto;
import com.userCommentApp.demo.dto.CommentDto;
import com.userCommentApp.demo.dto.converter.AccountCommentDtoConverter;
import com.userCommentApp.demo.dto.converter.CommentDtoConverter;
import com.userCommentApp.demo.entity.Account;
import com.userCommentApp.demo.entity.Comment;
import com.userCommentApp.demo.exception.NotFoundException;
import com.userCommentApp.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentDtoConverter commentDtoConverter;
    private final CommentRepository commentRepository;
    private final AccountCommentDtoConverter accountCommentDtoConverter;
    private final AccountService accountService;

    public CommentService(CommentDtoConverter commentDtoConverter, CommentRepository commentRepository, AccountCommentDtoConverter accountCommentDtoConverter, AccountService accountService) {
        this.commentDtoConverter = commentDtoConverter;
        this.commentRepository = commentRepository;
        this.accountCommentDtoConverter = accountCommentDtoConverter;
        this.accountService = accountService;
    }

    public CommentDto addComment(AddCommentRequest request,String id) {
        Account account = accountService.findAccountById(id);
        Comment comment = new Comment();
        comment.setText(request.getText());
        comment.setAccount(account);
        comment.setProductId(request.getProductId());
       return commentDtoConverter.convert(commentRepository.save(comment));
    }

    public List<AccountCommentDto> getCommentsByUser(String id) {
        Account account = accountService.findAccountById(id);
        return accountCommentDtoConverter.converToAccountComment(account.getComments(), account.getName());
    }

    public List<CommentDto> getCommentsByProductId(String id) {
        return commentDtoConverter.convertToCommentList(commentRepository.getCommentsByProductId(id), id);
    }

    public CommentDto deleteCommentById(String id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("comment not found"));

        commentRepository.delete(comment);
        return commentDtoConverter.convert(comment);
    }
}
