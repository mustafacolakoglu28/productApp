package com.userCommentApp.demo.controller;

import com.userCommentApp.demo.controller.request.comment.AddCommentRequest;
import com.userCommentApp.demo.controller.response.comment.DeleteCommentByIdResponse;
import com.userCommentApp.demo.controller.response.comment.GetCommentsByProductIdResponse;
import com.userCommentApp.demo.controller.response.comment.GetCommentsByUserResponse;
import com.userCommentApp.demo.controller.response.comment.AddCommentResponse;
import com.userCommentApp.demo.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<List<GetCommentsByUserResponse>> getCommentsByUser(@PathVariable("id") String id){
        return ResponseEntity.ok(GetCommentsByUserResponse.toResponse(commentService.getCommentsByUser(id)));
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<List<GetCommentsByProductIdResponse>> getCommentsByProductId(@PathVariable("id") String id){
        return ResponseEntity.ok(GetCommentsByProductIdResponse.toResponse(commentService.getCommentsByProductId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteCommentByIdResponse> deleteCommentById(@PathVariable("id") String id){
        return ResponseEntity.ok(DeleteCommentByIdResponse.toResponse(commentService.deleteCommentById(id)));

    }
}
