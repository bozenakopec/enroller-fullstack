<template>
  <div>
    <form @submit.prevent="addNewMeeting()" v-if="adding">
      <h3>Dodaj spotkanie</h3>
      <label>Nazwa</label>
      <input type="text" v-model="newMeeting.name">
      <label>Opis</label>
      <textarea v-model="newMeeting.description"></textarea>
      <button>Dodaj spotkkanie</button>
      <span class="error" v-if="error">Nie mozesz dodac spotkania bez nazwy!</span>
    </form>
    <button @click="adding = true" v-else>Dodaj spotkanie</button>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                newMeeting: {participants: []},
                adding: false,
                error: false
            };
        },
        methods: {
            addNewMeeting() {
                this.error = false;
                if (this.newMeeting.name) {
                    this.$emit('added', this.newMeeting);
                    this.newMeeting = {participants: []};
                    this.adding = false;
                } else {
                    this.error = true;
                }
            }
        }
    }
</script>

<style scoped>
  .error {
    color: #CD5C5C;
  }
</style>
