package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository; // 이제 추상화에만 의존함

    public MemberServiceImpl(MemberRepository memberRepository) { // 의존관계 주입
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
