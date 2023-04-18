package org.example;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
CityService.cityCreator();

        JDA bot = JDABuilder.createDefault("insert token here")
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.watching("South Park"))
                .addEventListeners(new BotListener())
                .build();

    }
}