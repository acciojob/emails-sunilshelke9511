package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

//public class Workspace extends Gmail{
//
//    private ArrayList<Meeting> calendar; // Stores all the meetings
//
//    public Workspace(String emailId) {
//        // The inboxCapacity is equal to the maximum value an integer can store.
//
//    }
//
//    public void addMeeting(Meeting meeting){
//        //add the meeting to calendar
//
//    }
//
//    public int findMaxMeetings(){
//        // find the maximum number of meetings you can attend
//        // 1. At a particular time, you can be present in at most one meeting
//        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
//        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
//
//    }
//}

public class Workspace extends Gmail{ {
    private List<Meeting> meetings;

    public Workspace(String name, int inboxCapacity) {
        super(inboxCapacity);
        this.name = name;
        this.meetings = new ArrayList<>();
    }

    public Workspace(int capacity) {
        super(capacity);
        this.meetings = new ArrayList<Meeting>();
    }

    public void scheduleMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }



    public List<Meeting> getMeetings() {
        return meetings;
    }

    public TimeSlot findMaxMeetings(List<Meeting> meetings, LocalTime start, LocalTime end) {
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        for (Meeting meeting : meetings) {
            timeSlots.add(meeting.getTimeSlot());
        }
        timeSlots.sort(null);
        TimeSlot maxTimeSlot = null;
        int maxMeetings = 0;
        for (int i = 0; i < timeSlots.size(); i++) {
            int j = i;
            int meetingsInSlot = 1;
            while (j < timeSlots.size() - 1 && timeSlots.get(j).getEndTime().isBefore(timeSlots.get(j + 1).getStartTime())) {
                j++;
                meetingsInSlot++;
            }
            if (meetingsInSlot > maxMeetings && timeSlots.get(i).getStartTime().isAfter(start) && timeSlots.get(j).getEndTime().isBefore(end)) {
                maxMeetings = meetingsInSlot;
                maxTimeSlot = new TimeSlot(timeSlots.get(i).getStartTime(), timeSlots.get(j).getEndTime());
            }
        }
        return maxTimeSlot;
    }
}