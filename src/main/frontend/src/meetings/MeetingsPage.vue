<template>
  <div>
    <new-meeting-form @added="addNewMeeting($event)"></new-meeting-form>

    <span v-if="meetings.length === 0">
              Nie masz zaplanowanych spotkan.
           </span>
    <h3 v-else>
      Liczba zaplanowanych spotkan ({{ meetings.length }})
    </h3>
<!--  wyswietlanie listy spotkan -->
    <meetings-list :meetings="meetings"
                   :username="username"
                   @attend="addParticipantToMeeting($event)"
                   @unattend="removeParticipantFromMeeting($event)"
                   @delete="deleteMeeting($event)"></meetings-list>
  </div>
</template>

<script>
    import NewMeetingForm from "./NewMeetingForm";
    import MeetingsList from "./MeetingsList";

    export default {
        components: {NewMeetingForm, MeetingsList},
        props: ['username'],
        data() {
            return {
                meetings: []
            };
        },
        methods: {
          //dodawanie spotkania
            addNewMeeting(meeting) {
                this.meetings.push(meeting);
            },
          //dodawanie uczestnika do spotkania
            addParticipantToMeeting(meeting) {
                meeting.participants.push(this.username);
            },
          //usuwanie uczestnika ze spotkania
            removeParticipantFromMeeting(meeting) {
                meeting.participants.splice(meeting.participants.indexOf(this.username), 1);
            },
          //usuwanie spotkania
            deleteMeeting(meeting) {
                this.meetings.splice(this.meetings.indexOf(meeting), 1);
            }
        }
    }
</script>

