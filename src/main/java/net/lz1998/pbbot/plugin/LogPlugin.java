package net.lz1998.pbbot.plugin;

import lombok.extern.slf4j.Slf4j;
import net.lz1998.pbbot.bot.Bot;
import net.lz1998.pbbot.bot.BotPlugin;
import onebot.OnebotEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogPlugin extends BotPlugin {
    @Override
    public int onPrivateMessage(@NotNull Bot bot, @NotNull OnebotEvent.PrivateMessageEvent event) {
        log.info("{\"群号\":\"999999999\",\"QQ\":\"{}\",\"内容\":\"{}\",\"发送时间\":\"{}\"}",  event.getUserId(), event.getRawMessage(),event.getTime());
        return MESSAGE_IGNORE;
    }

    @Override
    public int onGroupMessage(@NotNull Bot bot, @NotNull OnebotEvent.GroupMessageEvent event) {
        log.info("{\"群号\":\"{}\",\"QQ\":\"{}\",\"内容\":\"{}\",\"发送时间\":\"{}\"}", event.getGroupId(), event.getUserId(), event.getRawMessage(),event.getTime());
        return MESSAGE_IGNORE;
    }
}
