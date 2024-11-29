package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {
    @Inject
    private AuthenticationService toTest;

    private final SignIn signInMock = mock(SignIn.class);
    private final LogIn logInMock = mock(LogIn.class);

    @BeforeEach
    void setUp() {
        Injector injector = Guice.createInjector(
                new AbstractModule() {
                    @Override
                    protected void configure() {
                        bind(SignIn.class).toInstance(signInMock);
                        bind(LogIn.class).toInstance(logInMock);
                    }
                }
        );
        injector.injectMembers(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void signIn_succeeds() {
        // When
        when(signInMock.signInUser(eq(new String("test")), eq("1234"))).thenReturn(true);
        // Act
        boolean result = toTest.signIn(new String("test"), "1234");
        // Assert
        assertThat(result).isTrue();
        verify(signInMock).signInUser(any(), any());
        verifyZeroInteractions(logInMock);
    }

    @Test
    void logIn_succeeds() {
        // When
        when(logInMock.logInUser("test", "1234")).thenReturn(true);
        // Act
        boolean result = toTest.logIn("test", "1234");
        // Assert
        assertThat(result).isTrue();
    }
}