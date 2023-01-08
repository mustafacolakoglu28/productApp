package com.userCommentApp.demo.service;

import com.userCommentApp.demo.controller.request.comment.AddCommentRequest;
import com.userCommentApp.demo.dto.CommentDto;
import com.userCommentApp.demo.dto.converter.AccountDtoConverter;
import com.userCommentApp.demo.dto.converter.CommentDtoConverter;
import com.userCommentApp.demo.entity.Account;
import com.userCommentApp.demo.entity.Comment;
import com.userCommentApp.demo.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentDtoConverter commentDtoConverter;
    private final CommentRepository commentRepository;
    private final AccountService accountService;

    public CommentService(CommentDtoConverter commentDtoConverter, CommentRepository commentRepository, AccountService accountService) {
        this.commentDtoConverter = commentDtoConverter;
        this.commentRepository = commentRepository;
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
}
