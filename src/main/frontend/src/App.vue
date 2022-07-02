<template>
  <div id="app">
    <h1>
      <img src="./assets/logo.svg" alt="Enroller" class="logo">
      Zapisy na zajęcia
    </h1>
    <div v-if="authenticatedUsername">
      <h2>Witaj:){{ authenticatedUsername }}
        <a @click="logout()" class="float-right  button-outline button">Wyloguj</a>
      </h2>
      <meetings-page :username="authenticatedUsername"></meetings-page>
    </div>

    <div v-else>
      <div class="error-msg" v-if="errorMessage"> {{ errorMessage }}</div>
      <login-form @login="submitLoginOrRegister($event)" :button-label=this.loginOrRegisterButtonLabel></login-form>
      <div v-if="shouldRegister">
        <a @click=shouldLogin()>Zaloguj</a>
      </div>
      <div v-else>
        <p>Nie masz konta? <a @click=shouldRegister()>Zarejestruj się</a></p>
      </div>
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
                authenticatedUsername: "",
                shouldRegister: false,
                loginOrRegisterButtonLabel: "Zaloguj",
                errorMessage: ''
            };
        },
        methods: {
            submitLoginOrRegister(user) {
                if (this.shouldRegister) {
                  this.register(user);
                } else {
                    this.login(user);
                }

            },
            logout() {
                this.authenticatedUsername = '';
                this.errorMessage = '';
            },
            shouldRegister() {
              this.shouldRegister = true;
              this.loginOrRegisterButtonLabel = "Zarejestruj"
            },

            shouldLogin() {
              this.shouldRegister = false;
              this.loginOrRegisterButtonLabel = "Zaloguj"
            },

            login(user) {
                this.authenticatedUsername = user;
            },


            register(user) {
                this.$http.post('participants', user)
                    .then(() => {
                        this.authenticatedUsername = user.login;
                    })
                    .catch(response => {
                        this.errorMessage = response.bodyText;
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

  .error-msg {
    color: #CD5C5C;
  }

</style>
