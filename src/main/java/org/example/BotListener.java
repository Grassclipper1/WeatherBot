package org.example;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BotListener extends ListenerAdapter {


    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String messageContent = event.getMessage().getContentRaw();
        MessageChannel channel = event.getChannel();

        if (messageContent.equalsIgnoreCase("!test")) {
            channel.sendMessage("I am Awesome-O").queue();
        } else if (messageContent.toLowerCase().startsWith("!väder")) {
            String[] stad = messageContent.split(" ");
            if (stad.length > 1) {
                String city = messageContent.toLowerCase().substring(stad[0].length() + 1);
                weatherChecker(city, channel);
            } else if (messageContent.toLowerCase().startsWith("!google")) {
                String[] tokens = messageContent.split(" ");
                if (tokens.length > 1) {
                    String query = messageContent.substring(tokens[0].length() + 1);
                    channel.sendMessage("https://www.google.com/search?q=" + query).queue();
                }
            } else if (messageContent.contains(event.getJDA().getSelfUser().getAsMention())) {
                channel.sendMessage("But mooooooom").queue();
            }
        }
    }

    private void weatherChecker(String city, MessageChannel channel) {
        String stuff = CityService.cityFinder(city);
        String bigCity = city.substring(0, 1).toUpperCase() + city.substring(1);
        if (stuff == null) {
            channel.sendMessage("" + bigCity + " finns inte i systemet").queue();
        } else {
            HttpResponse<String> response = Unirest.get(CityService.cityFinder(city))
                    .header("X-RapidAPI-Key", "19f57ba288mshd054166829f3644p1b1c3ajsn0845d5fccbfe")
                    .header("X-RapidAPI-Host", "weather1395.p.rapidapi.com")
                    .asString();
            channel.sendMessage("Det är " + WeatherParser.parser(response) + "\u00B0C i " + bigCity).queue();
        }
    }
}

