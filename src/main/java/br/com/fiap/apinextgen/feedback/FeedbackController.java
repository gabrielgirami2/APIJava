package br.com.fiap.apinextgen.feedback;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping
    public List<Feedback> getAll() {
        return feedbackService.getAllFeedbacks();
    }

    @GetMapping("{id}")
    public Feedback getById(@PathVariable Long id) {
        return feedbackService.getById(id);
    }

    @PostMapping
    public Feedback post(@RequestBody Feedback f) {
        return feedbackService.createFeedback(f);
    }

    @PutMapping("{id}")
    public Feedback update(@RequestBody Feedback f, @PathVariable Long id) {
        return feedbackService.updateFeedback(f, id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
    }

}
