package com.phantom.cat.repository;

import com.phantom.cat.model.Catm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CatmRepository extends MongoRepository<Catm, String> {

    // ✅ BEST WAY: FULL CONVERSATION (USE THIS ONLY)
    @Query("{ '$or' : [ " +
            "{ 'from' : ?0 , 'to' : ?1 }, " +
            "{ 'from' : ?1 , 'to' : ?0 } " +
            "] }")
    List<Catm> findConversation(String user1, String user2);

    // OPTIONAL: only if you need direct messages
    List<Catm> findByFromAndTo(String from, String to);
}