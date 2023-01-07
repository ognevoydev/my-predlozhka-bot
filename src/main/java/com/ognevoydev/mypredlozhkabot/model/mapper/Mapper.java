package com.ognevoydev.mypredlozhkabot.model.mapper;

import com.ognevoydev.mypredlozhkabot.model.Media;
import com.ognevoydev.mypredlozhkabot.model.MediaType;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.util.List;

import static com.ognevoydev.mypredlozhkabot.constants.ReplyMessages.ADMIN_POST_REPLY;
import static com.ognevoydev.mypredlozhkabot.utils.Utils.*;

public class Mapper {

    public static Media messageToMedia(Message message) {
        String caption = String.format(ADMIN_POST_REPLY, message.getChat().getFirstName());

        switch (getMediaType(message)) {
            case PHOTO -> {
                List<PhotoSize> photos = message.getPhoto();
                InputFile file = new InputFile(getMaxSizePhoto(photos));

                return new Media(caption, file, MediaType.PHOTO);
            }
            case VIDEO -> {
                String video = message.getVideo().getFileId();
                InputFile file = new InputFile(video);

                return new Media(caption, file, MediaType.VIDEO);
            }
            default -> {
                String animation = message.getAnimation().getFileId();
                InputFile file = new InputFile(animation);

                return new Media(caption, file, MediaType.ANIMATION);
            }
        }
    }

}
