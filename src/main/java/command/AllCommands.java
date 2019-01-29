package command;

import command.changes.NewEmail;
import command.changes.NewLogin;
import command.changes.NewPassword;

public enum AllCommands {
    LOGIN(new Login()),
    LANGUAGE(new Language()),
    REGISTRATION(new Registration()),
    MAIN(new Main()),
    LOGOUT(new Logout()),
    NEW_LOGIN(new NewLogin()),
    NEW_PASSWORD(new NewPassword()),
    NEW_EMAIL(new NewEmail()),
    ADD_CRYPTO_ORDER(new AddCryptoOrder()),
    DELETE_CRYPTO_ORDER(new DeleteCryptoOrder()),
    ADD_ORDER(new AddOrder()),
    VIEW_MY_CRYPTO(new ViewMyCrypto()),
    SELL_CRYPTO_ORDER(new SellCrypto());

    ActionCommand commands;

    AllCommands(ActionCommand commands) {
        this.commands = commands;
    }

    public ActionCommand getCommand(){
        return commands;
    }
}
