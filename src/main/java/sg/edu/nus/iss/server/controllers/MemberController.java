package sg.edu.nus.iss.server.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

import jakarta.json.JsonObject;
import sg.edu.nus.iss.server.models.Member;
import sg.edu.nus.iss.server.repositories.MemberRepository;

@Controller
@RequestMapping(path = "/member")
@CrossOrigin(origins = "*")
public class MemberController {
    
    @Autowired
    private MemberRepository memberRepo;

    // https://configmysql-production.up.railway.app/member/add
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/add")
    public ResponseEntity<String> saveMember(@RequestPart String name, @RequestPart String telegram, @RequestPart String grade) {
        System.out.println(name);
        System.out.println(telegram);
        System.out.println(grade);
        // Create member based on data from form filled by user
        Member member = new Member();
        member.setName(name);
        member.setTelegram(telegram);
        member.setGrade(grade);
        JsonObject memberJson = member.toJSON();
        memberRepo.save(member);
        return ResponseEntity.ok(memberJson.toString());
    }

    // https://configmysql-production.up.railway.app/member/{telegram}
    @GetMapping(path = "{telegram}")
    public ResponseEntity<String> getMember(@PathVariable String telegram) {
        System.out.println(telegram);
        Optional<Member> optMember = memberRepo.get(telegram);
        Member member = new Member();
        if (optMember.isEmpty()) {
            member.setName("Not set");
            member.setTelegram("Not set");
            member.setGrade("Not set");
        } else {
            member = optMember.get();
        }
        return ResponseEntity.ok(member.toJSON().toString());
    }
}
