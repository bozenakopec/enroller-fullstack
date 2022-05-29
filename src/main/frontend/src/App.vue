<template>
  <div id="app">
    <h1>
      <img src="./assets/logo.svg" alt="Enroller" class="logo">
      System do zapisów na zajęcia
    </h1>
    <div v-if="authenticatedUsername">
      <h2>Witaj {{ authenticatedUsername }}!
        <a @click="logout()" class="float-right  button-outline button">Wyloguj</a>
      </h2>
      <meetings-page :username="authenticatedUsername"></meetings-page>
    </div>
    <div v-else>

      <button @click="registering=false" :class="registering ? 'button button-clear' : ''" > Zaloguj </button>
      <button  @click="registering=true" :class="registering ? '' : 'button button-clear'" > Zarejestruj </button>

      <div v-if="errorMessage">{{ errorMessage}}</div>
      <div v-if="successMessage">{{successMessage}}</div>

      <login-form @login="login($event)" v-if="registering==false"></login-form>
      <login-form @login="register($event)" v-else button-label="Zarejestruj się"></login-form>
    </div>
  </div>
</template>

<script>
    import "milligram";
    import LoginForm from "./LoginForm";
    import MeetingsPage from "./meetings/MeetingsPage";

    export default {
        components: {LoginForm, MeetingsPage},
        data() {
            return {
              registering: false,
                authenticatedUsername: "",
              errorMessage: ' ',
              successMessage: ' ',
            };
        },
        methods: {
            login(user) {
                this.authenticatedUsername = user.login;
            },
            logout() {
                this.authenticatedUsername = '';
            },
          register(user){
              this.successMessage = '';
              this.errorMessage = '';
              this.$http.post("participants", user)
            .then(response=> {
              this.succcessMessage = 'Super, masz już konto';
              // udało się
            })
            .catch(response=>{
              console.log(response);
              this.errorMessage = 'Nie udało sie'
              // nie udało się
            });

          }
        }
    };
</script>

<style>
  #app {
    max-width: 1000px;
    margin: 0 auto;
  }

  .logo {
    vertical-align: middle;
  }
</style>

