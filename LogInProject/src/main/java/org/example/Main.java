package org.example;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(
                new AbstractModule() {
                    @Override
                    public void configure() {
                        bind(PassWordHashing.class).toInstance(new Sha256Hashing());
                    }
                }
        );

        ConsoleApplication app = injector.getInstance(ConsoleApplication.class);
        app.run();
    }
}
