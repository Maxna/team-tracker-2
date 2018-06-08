package dao;

import models.Member;
import java.util.List;

public interface MemberDao {
    // LIST
    List<Member> getAll();

    // CREATE
    void add(Member member);

    // READ
    Member findByID(int id);

    // UPDATE
    void update(int id, String member);

    // DELETE
    void deleteById(int id);
    void clearAllMembers();
}
