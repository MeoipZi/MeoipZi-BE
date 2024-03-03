package MeoipZi.loginMeoipZi.repository;

import MeoipZi.loginMeoipZi.domain.CommentCommunity;
import MeoipZi.loginMeoipZi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentCommunityRepository extends JpaRepository<CommentCommunity, Long> {

    @Query("SELECT c FROM CommentCommunity c WHERE c.user = ?1 GROUP BY c.community.id ORDER BY MAX(c.createdAt) DESC")
    List<CommentCommunity> findTop3DistinctCommentsByUserOrderByCreatedAtDesc(User user);
}
