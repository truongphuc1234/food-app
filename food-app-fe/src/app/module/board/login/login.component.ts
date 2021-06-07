import {Component, OnInit} from '@angular/core';
import {TokenStorageService} from '../../../service/authorization/token-storage';
import {AuthenticationService} from '../../../service/authorization/authentication-service';
import JwtRequest from '../../../service/authorization/JwtRequest';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {faGoogle} from '@fortawesome/free-brands-svg-icons';
import {faFacebook} from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public formLogin: FormGroup;
  public faGoogle = faGoogle;
  public faFacebook = faFacebook;

  constructor(public tokenStorageService: TokenStorageService,
              public authenticationService: AuthenticationService,
              public formBuilder: FormBuilder,
              public matDialog: MatDialog) {
  }

  ngOnInit(): void {
    this.formLogin = this.formBuilder.group({
      accountName: [],
      password: [],
      remember: [],
    });
  }

  logout() {
    this.tokenStorageService.logOut();
    window.location.reload();
  }

  login() {
    this.authenticationService.sendLogin(new JwtRequest(this.formLogin.controls.accountName?.value,
      this.formLogin.controls.password?.value))
      .subscribe(data => {
      if (data.jwtToken === null) {
      } else {
        if (data.jwtToken === 'INVALID_CREDENTIALS') {
        } else {
          this.tokenStorageService.saveData(data, this.formLogin.controls.remember?.value);
        }
      }
      this.matDialog.closeAll();
    });
  }

}
