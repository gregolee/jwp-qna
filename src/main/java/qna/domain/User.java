package qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import qna.UnAuthorizedException;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(length = 50)
	private String email;

	@Column(nullable = false, length = 20)
	private String name;

	@Column(nullable = false, length = 20)
	private String password;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "user_id", nullable = false, length = 20, unique = true)
	private String userId;

	protected User() {
	}

	public User(Long id, String userId, String password, String name, String email) {
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public void update(User loginUser, User target) {
		if (!matchUserId(loginUser.userId)) {
			throw new UnAuthorizedException();
		}

		if (!matchPassword(target.password)) {
			throw new UnAuthorizedException();
		}

		this.name = target.name;
		this.email = target.email;
	}

	private boolean matchUserId(String userId) {
		return this.userId.equals(userId);
	}

	public boolean matchPassword(String targetPassword) {
		return this.password.equals(targetPassword);
	}

	public boolean equalsNameAndEmail(User target) {
		if (Objects.isNull(target)) {
			return false;
		}

		return name.equals(target.name) && email.equals(target.email);
	}

	public Long id() {
		return id;
	}

	public String userId() {
		return userId;
	}

	public String password() {
		return password;
	}

	public String name() {
		return name;
	}

	public void changeName(String name) {
		this.name = name;
	}

	public String email() {
		return email;
	}

	public void changeEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User{"
			+ "id=" + id
			+ ", userId='" + userId + '\''
			+ ", password='" + password + '\''
			+ ", name='" + name + '\''
			+ ", email='" + email + '\''
			+ '}';
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (!(object instanceof User)) {
			return false;
		}
		User user = (User) object;
		return Objects.equals(id, user.id)
			&& Objects.equals(createdAt, user.createdAt)
			&& Objects.equals(email, user.email)
			&& Objects.equals(name, user.name)
			&& Objects.equals(password, user.password)
			&& Objects.equals(updatedAt, user.updatedAt)
			&& Objects.equals(userId, user.userId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, createdAt, email, name, password, updatedAt, userId);
	}
}
