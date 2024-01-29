package hello.world.v1.model;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// db table과 1대1일 매핑
// DBMS 테이블명과 엔티티명 동일하게
// 아니면 따로 설정
@Entity
@Getter
@Setter // Entity에서는 setter 사용을 권장하지 않음
@NoArgsConstructor(access = AccessLevel.PRIVATE) // 필수(JPA에서 정함)
//@Table(name=)
public class Member {
	@Id // 기본키
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동증가
	private Long id;

	@Column(name = "username", unique = true) // 실제 테이블 컬럼명, 같으면 생략 가능
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

	public void encodePassword(PasswordEncoder passwordEncoder) {
//		this.password = "{noop}" + this.password;
		this.password = passwordEncoder.encode(this.password);
	}
}
