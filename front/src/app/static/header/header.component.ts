import {Component, OnInit} from '@angular/core';
import {environment} from "../../../environments/environment";
import {User} from "../../interfaces/user";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  isLoggedIn = false;
  user !: User;

  profileLink: string;

  constructor(private userService: UserService) {
    this.profileLink = userService.getProfileLink();
  }

  ngOnInit(): void {
    this.checkUserIsLoggedInAndWhoAmI();
  }


  checkUserIsLoggedInAndWhoAmI(): void {
    this.userService.isLoggedIn().then(status => {
      this.isLoggedIn = status;

      if (status) {
        this.userService.check().then(data => {
          this.user = data;
        })
      }
    })
  }
}
