import {Component, OnInit} from '@angular/core';
import {LoginComponent} from '../login/login.component';
import {MatDialog} from '@angular/material/dialog';
import {TokenStorageService} from '../../../service/authorization/token-storage';
import {Customer} from '../../../model/customer.model';
import {Account} from '../../../model/account.model';
import {CustomerBackendService} from '../../../service/customer/customer-backend.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
  public account: Account;

  constructor(public dialog: MatDialog,
              public tokenStorageService: TokenStorageService,
              public customerBackendService: CustomerBackendService) {
  }

  ngOnInit(): void {
    this.account = this.tokenStorageService.getAccount();
  }

  openDialog() {
    const dialogRef = this.dialog.open(LoginComponent);
    dialogRef.afterClosed().subscribe(() => {
      this.account = this.tokenStorageService.getAccount();
    });
  }

  logout() {
    this.tokenStorageService.logOut();
  }
}
