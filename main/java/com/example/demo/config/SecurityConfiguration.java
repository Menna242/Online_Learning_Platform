package com.example.demo.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  throws Exception{
        return config.getAuthenticationManager();

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/auth/CreateOffical").hasRole("ADMIN")
                        .requestMatchers("/auth/CreateStudent").hasRole("ADMIN")
                        .requestMatchers("/Profile/viewProfile/{USER_ID}").permitAll()
                        .requestMatchers("/Profile/updateAdmin/{USER_ID}").hasRole("ADMIN")

                        .requestMatchers("/auth/addCourse").hasRole("INSTRUCTOR")
                        .requestMatchers("auth/deleteCourse/{id}").hasRole("INSTRUCTOR")
                        .requestMatchers("auth/allCourses").permitAll()
                        .requestMatchers("auth/course/instructor/{instructorId}").permitAll()
                        .requestMatchers("auth/getCourse/{id}").permitAll()
                        .requestMatchers("auth/update/{id}").hasRole("INSTRUCTOR")

                        .requestMatchers("auth/deleteLesson/{id}").hasRole("INSTRUCTOR")
                        .requestMatchers("auth/addLesson").hasRole("INSTRUCTOR")
                        .requestMatchers("auth/getLessonsByCourse/{courseid}").permitAll()

                        .requestMatchers("auth/addEnrollment").hasRole("STUDENT")
                        .requestMatchers("auth/course/{courseId}").hasAnyRole("INSTRUCTOR","ADMIN")//!

                        .requestMatchers("auth/{notifyID}/read").hasAnyRole("INSTRUCTOR","STUDENT")  //!
                        .requestMatchers("auth/getAllNotification/{userid}").hasAnyRole("INSTRUCTOR","STUDENT")   //!
                        .requestMatchers("auth/unread/{userid}").hasAnyRole("INSTRUCTOR","STUDENT")  //!


                        .requestMatchers("auth/attendence").hasRole("STUDENT")
                        .requestMatchers("auth/lessonAttendence/{lessonid}").hasRole("INSTRUCTOR") //!


                        .requestMatchers("/auth/assignment/{assignmentId}/submit/{studentId}").hasRole("STUDENT")
                        .requestMatchers("/auth/assignment/{assignmentId}/grade").hasRole("INSTRUCTOR")
                        .requestMatchers("/auth/performance/student/{studentId}").hasRole("INSTRUCTOR")
                        .requestMatchers("/auth/assignment/create").hasRole("INSTRUCTOR")

                        .requestMatchers("/auth/{quizId}/add-question").hasRole("INSTRUCTOR")
                        .requestMatchers("/auth/create").hasRole("INSTRUCTOR")
                        .requestMatchers("/auth/{id}").hasRole("INSTRUCTOR")
                        .requestMatchers("/auth/getAll").hasRole("INSTRUCTOR")
                        .requestMatchers("/auth/course/{courseId}/create-quiz").hasRole("INSTRUCTOR")
                        .requestMatchers("/auth/{quizId}/submit").hasRole("STUDENT")

                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
    }
}
