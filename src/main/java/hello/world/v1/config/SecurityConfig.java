package hello.world.v1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
//	UserDetailsServiceAutoConfiguration a;
	
//	@Autowired
//	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("test").password("{noop}1111").roles("USER")
//			.and()
//			.withUser("admin1").password("{noop}1111").roles("ADMIN");
//	}
	
	
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrf)->csrf.disable())
			.authorizeHttpRequests((authorizeRequest)->
					authorizeRequest
						.requestMatchers("/","/info","/join/**","/login","/css/**").permitAll()
						.requestMatchers("/admin").hasRole("ADMIN")
						.anyRequest().authenticated())
						.exceptionHandling(error->error
								.accessDeniedPage("/access-denied"))
//			.formLogin(Customizer.withDefaults());
			.formLogin(formLogin->
				formLogin
					.loginPage("/login-form.html")
					.defaultSuccessUrl("/", true)
					.failureUrl("/login?error=true")
					.usernameParameter("username")
					.passwordParameter("password")
					.loginProcessingUrl("/login")
			);
		
		return http.build();
	}
	
	

}
