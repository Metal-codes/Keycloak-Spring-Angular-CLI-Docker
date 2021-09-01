import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {KeycloakService} from "keycloak-angular";
import {User} from "../interfaces/user";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private profileLink = window.location.origin + "/auth/realms/" + environment.keycloak.config.realm + "/account/";
  private route = '/user'

  constructor(
    private keycloakService: KeycloakService,
    private http: HttpClient
  ) {
  }

  /**
   * Redirects client to Keycloak Authorization page - logout
   * @param uri redirects to this url after logout
   */
  logout(uri?: string): void {
    this.keycloakService.logout(uri).then();
  }

  /**
   * Redirect client to Keycloak Authorization page - login
   * @param options options for Keycloak Login Options like redirectUri etc.
   */
  login(options?: Keycloak.KeycloakLoginOptions): void {
    this.keycloakService.login(options).then();
  }

  /**
   * Redirect client to Keycloak Authorization page - register
   * @param options options for Keycloak Register Options like redirectUri etc.
   */
  sign(options?: Keycloak.KeycloakRegisterOptions): void {
    this.keycloakService.register(options).then();
  }

  /**
   * Checks that client is logged in
   */
  isLoggedIn(): Promise<boolean> {
    return this.keycloakService.isLoggedIn();
  }

  /**
   * Checks that backend server is configured well.
   */
  check(): Promise<User> {
    return this.http.get<User>(`${environment.backendUrl}${this.route}`).toPromise()
  }

  /**
   * Getter for account settings url
   * @return url to account settings
   */
  getProfileLink(): string {
    return this.profileLink;
  }

}
