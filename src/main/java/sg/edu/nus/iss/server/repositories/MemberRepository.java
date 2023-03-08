package sg.edu.nus.iss.server.repositories;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.server.models.Member;

import static sg.edu.nus.iss.server.repositories.Queries.*;

@Repository
public class MemberRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Save Member object to MySQL Database
    public Boolean save(Member member) {
        Optional<Member> optMember = get(member.getTelegram());
        int rows = 0;
        if (optMember.isEmpty()) {
            rows = jdbcTemplate.update(SQL_INSERT_INTO_MEMBERS, member.getName(), member.getTelegram(), member.getGrade(), (new Date()).toString());
        } else {
            rows = jdbcTemplate.update(SQL_UPDATE_MEMBER_BY_TELEGRAM, member.getName(), member.getGrade(), (new Date()).toString(), member.getTelegram());
        }
        return rows > 0;
    }

    // Get Member object from MySQL Database by telegram ID
    public Optional<Member> get(String telegram) {
        Member member = new Member();
        // Retrieve rows of result which satisfy telegram ID
        SqlRowSet srs = jdbcTemplate.queryForRowSet(SQL_SELECT_MEMBER_BY_TELEGRAM, telegram);
        // Create a list to store all the members
        List<Member> members = new LinkedList<>();
        while (srs.next()) {
            System.out.println("Result returned");
            member = Member.create(srs);
            members.add(member);
        }
        // If list is empty, i.e. no result returned from query, return empty Box
        if (members.size() == 0) {
            return Optional.empty();
        }
        // Return first member in Box
        return Optional.of(members.get(0));
    }
}
