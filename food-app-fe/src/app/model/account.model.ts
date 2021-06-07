import {Customer} from './customer.model';

export interface Account {
  accountId: number;
  accountName?: string;
  accountStatus?: AccountStatus;
  accountRegisterTime?: Date;
  accountLoginTime?: Date;
  customer?: Customer;
}

export interface AccountStatus {
  accountStatusId?: number;
  accountStatusName?: string;
}

