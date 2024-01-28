package hello.world.v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hello.world.v1.model.Member;

/*
 * - 인터페이스 생성 후, JpaRepository<Entity 클래스, PK 타입>을 상속하면,
 * 기본적인 CRUD 메서드가 자동으로 생성
 * - @Repository 설정 필요 없음
 */

public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByUsername(String username);	// JPA에 없는 메서드 => 자동 생성
}
