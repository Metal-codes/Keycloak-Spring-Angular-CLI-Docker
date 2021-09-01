import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {UserService} from "../../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit, AfterViewInit {

  @ViewChild("errorMsg", {read: ElementRef, static: true})
  errorMessage !: ElementRef;

  constructor(
    private userService: UserService,
    private router: Router
  ) {
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    this.userService.isLoggedIn().then(status => {
      if (status) {
        this.userService.logout(window.location.origin);
      } else {
        this.errorMessage.nativeElement.innerText = "You can't logout when you are not logged in.";
        this.redirectAfterFiveSeconds();
      }
    })
  }

  redirectAfterFiveSeconds(): void {
    setTimeout(() => {
      this.router.navigate(['']).then();
    }, 5000);
  }
}
