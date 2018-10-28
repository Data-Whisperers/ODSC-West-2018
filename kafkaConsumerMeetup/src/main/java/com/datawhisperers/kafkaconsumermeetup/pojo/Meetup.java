/*
 * Copyright 2018 Data Whisperers LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datawhisperers.kafkaconsumermeetup.pojo;

public class Meetup {

    private Venue venue;
    private String visibility;
    private String response;
    private String guests;
    private Member member;
    private String rsvp_id;
    private String mtime;
    private Event event;
    private Group group;

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getGuests() {
        return guests;
    }

    public void setGuests(String guests) {
        this.guests = guests;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getRsvp_id() {
        return rsvp_id;
    }

    public void setRsvp_id(String rsvp_id) {
        this.rsvp_id = rsvp_id;
    }

    public String getMtime() {
        return mtime;
    }

    public void setMtime(String mtime) {
        this.mtime = mtime;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Meetup{" + "venue=" + venue + ", visibility=" + visibility + ", response=" + response + ", guests=" + guests + ", member=" + member + ", rsvp_id=" + rsvp_id + ", mtime=" + mtime + ", event=" + event + ", group=" + group + '}';
    }

}
