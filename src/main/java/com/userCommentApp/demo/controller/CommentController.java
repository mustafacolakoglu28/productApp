package com.userCommentApp.demo.controller;

import com.userCommentApp.demo.controller.request.comment.AddCommentRequest;
import com.userCommentApp.demo.controller.response.comment.AddCommentResponse;
import com.userCommentApp.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("{id}")
    public ResponseEntity<AddCommentResponse> addComment(@PathVariable("id") String id,
                                                         @Valid @RequestBody AddCommentRequest request){
        return ResponseEntity.ok(AddCommentResponse.toResponse(commentService.addComment(request,id)));
    }
}
