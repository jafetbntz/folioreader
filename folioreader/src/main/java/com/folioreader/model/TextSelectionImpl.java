package com.folioreader.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * This data structure holds information about an individual highlight.
 *
 * @author mahavir on 5/12/16.
 */

public class TextSelectionImpl implements Parcelable, TextSelection {

    public static final String INTENT = TextSelectionImpl.class.getName();
    public static final String BROADCAST_EVENT_ADD = "add_broadcast_event";
    public static final String BROADCAST_EVENT_TRANSLATE = "translate_broadcast_event";

    /**
     * Database id
     */
    protected int id;
    /**
     * <p> Book id, which can be provided to intent to folio reader, if not provided id is
     * used from epub's dc:identifier field in metadata.
     * <p>for reference, look here:
     * <a href="http://www.idpf.org/epub/30/spec/epub30-publications.html#sec-package-metadata-identifiers">IDPF</a>.</p>
     * in case identifier is not found in the epub,
     * <a href="https://docs.oracle.com/javase/7/docs/api/java/lang/String.html#hashCode()">hash code</a>
     * of book title is used also if book title is not found then
     * hash code of the book file name is used.
     * </p>
     */
    protected String bookId;
    /**
     * Highlighted text content text content.
     */
    protected String content;
    /**
     * Date time when highlight is created (format:- MMM dd, yyyy | HH:mm).
     */
    protected Date date;
    /**
     * Page index in the book taken from Epub spine reference.
     */
    protected int pageNumber;
    /**
     * href of the page from the Epub spine list.
     */
    protected String pageId;
    /**
     * <p> Contains highlight meta data in terms of rangy format.</p>
     * <strong>format </strong>:- start$end$id$class$containerId.
     * <p>for reference, look here: <a href="https://github.com/timdown/rangy">rangy</a>.</p>
     */
    protected String rangy;

    /**
     * Unique identifier for a highlight for sync across devices.
     * <p>for reference, look here:
     * <a href = "https://docs.oracle.com/javase/7/docs/api/java/util/UUID.html#toString()">UUID</a>.</p>
     */
    protected String uuid;

    /**
     * Note linked to the highlight (optional)
     */
    protected String note;

    public TextSelectionImpl(int id, String bookId, String content, Date date,
                             int pageNumber, String pageId,
                             String rangy, String note, String uuid) {
        this.id = id;
        this.bookId = bookId;
        this.content = content;
        this.date = date;
        this.pageNumber = pageNumber;
        this.pageId = pageId;
        this.rangy = rangy;
        this.note = note;
        this.uuid = uuid;
    }

    public TextSelectionImpl() {
    }

    protected TextSelectionImpl(Parcel in) {
        readFromParcel(in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getRangy() {
        return rangy;
    }

    public void setRangy(String rangy) {
        this.rangy = rangy;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getNote() {
        return note;
    }

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSelectionImpl textSelectionImpl = (TextSelectionImpl) o;

        return id == textSelectionImpl.id
                && (bookId != null ? bookId.equals(textSelectionImpl.bookId) : textSelectionImpl.bookId == null
                && (content != null ? content.equals(textSelectionImpl.content) : textSelectionImpl.content == null
                && (date != null ? date.equals(textSelectionImpl.date) : textSelectionImpl.date == null)));
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (bookId != null ? bookId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "HighlightImpl{" +
                "id=" + id +
                ", bookId='" + bookId + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
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
        pageNumber = in.readInt();
        note = in.readString();
        uuid = in.readString();
    }

    public static final Creator<TextSelectionImpl> CREATOR = new Creator<TextSelectionImpl>() {
        @Override
        public TextSelectionImpl createFromParcel(Parcel in) {
            return new TextSelectionImpl(in);
        }

        @Override
        public TextSelectionImpl[] newArray(int size) {
            return new TextSelectionImpl[size];
        }
    };
}
