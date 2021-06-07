import { Account} from "../../model/account.model";

export class JwtResponse {
    jwtToken: string;
    account: Account;
    roles: string[];
    constructor(jwtToken: string) {
        this.jwtToken = jwtToken;
    }
}
