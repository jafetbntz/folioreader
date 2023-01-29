package com.folioreader.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

/**
 * This data structure holds information about an individual highlight.
 *
 * @author mahavir on 5/12/16.
 */

public class HighlightImpl extends TextSelectionImpl implements Parcelable, HighLight {

    public static final String INTENT = HighlightImpl.class.getName();
    public static final String BROADCAST_EVENT = "highlight_broadcast_event";

    /**
     * Field defines the color of the highlight. {@link HighlightStyle}
     */
    private String type;

    public enum HighlightStyle {
        Yellow,
        Green,
        Blue,
        Pink,
        Underline,
        TextColor,
        DottetUnderline,
        Normal;

        /**
         * Return CSS class for HighlightStyle.
         */
        public static String classForStyle(HighlightStyle style) {
            switch (style) {
                case Yellow:
                    return "highlight_yellow";
                case Green:
                    return "highlight_green";
                case Blue:
                    return "highlight_blue";
                case Pink:
                    return "highlight_pink";
                case Underline:
                    return "highlight_underline";
                case DottetUnderline:
                    return "mediaOverlayStyle1";
                case TextColor:
                    return "mediaOverlayStyle2";
                default:
                    return "mediaOverlayStyle0";

            }
        }
    }

    public HighlightImpl(int id, String bookId, String content, Date date, String type,
                         int pageNumber, String pageId,
                         String rangy, String note, String uuid) {
        this.id = id;
        this.bookId = bookId;
        this.content = content;
        this.date = date;
        this.type = type;
        this.pageNumber = pageNumber;
        this.pageId = pageId;
        this.rangy = rangy;
        this.note = note;
        this.uuid = uuid;
    }

    public HighlightImpl() {
    }

    protected HighlightImpl(Parcel in) {
        readFromParcel(in);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HighlightImpl highlightImpl = (HighlightImpl) o;

        return super.equals(o) && (type != null ? type.equals(highlightImpl.type) : highlightImpl.type == null);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HighlightImpl{" +
                "id=" + id +
                ", bookId='" + bookId + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", pageNumber=" + pageNumber +
                ", pageId='" + pageId + '\'' +
                ", rangy='" + rangy + '\'' +
                ", note='" + note + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(bookId);
        dest.writeString(pageId);
        dest.writeString(rangy);
        dest.writeString(content);
        dest.writeSerializable(date);
        dest.writeString(type);
        dest.writeInt(pageNumber);
        dest.writeString(note);
        dest.writeString(uuid);
    }

    private void readFromParcel(Parcel in) {
        id = in.readInt();
        bookId = in.readString();
        pageId = in.readString();
        rangy = in.readString();
        content = in.readString();
        date = (Date) in.readSerializable();
        type = in.readString();
        pageNumber = in.readInt();
        note = in.readString();
        uuid = in.readString();
    }

    public static final Creator<HighlightImpl> CREATOR = new Creator<HighlightImpl>() {
        @Override
        public HighlightImpl createFromParcel(Parcel in) {
            return new HighlightImpl(in);
        }

        @Override
        public HighlightImpl[] newArray(int size) {
            return new HighlightImpl[size];
        }
    };

    public String toJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
