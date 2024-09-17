package br.com.fiap.apinextgen.feedback;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    public Feedback getById(Long id) {
        return verifyExistence(id);
    }

    public Feedback createFeedback(Feedback f) {
        return feedbackRepository.save(f);
    }

    public Feedback updateFeedback(Feedback f, Long id) {
        verifyExistence(id);
        f.setId(id);
        return feedbackRepository.save(f);
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
    }

    private Feedback verifyExistence(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }

}
