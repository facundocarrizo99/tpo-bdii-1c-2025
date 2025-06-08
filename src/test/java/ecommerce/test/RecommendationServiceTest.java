package ecommerce.test;

import ecommerce.recommendation.RecommendationService;

import java.util.List;


public class RecommendationServiceTest {
    public static void main(String[] args) {
        RecommendationService recommendationService = new RecommendationService();

        // Test recomnder
        List<String> list = recommendationService.getRecommendations("santi");
        System.out.println("Recommendations for user 'santi': " + list);

    }
}
