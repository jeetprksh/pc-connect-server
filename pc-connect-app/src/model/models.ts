/*
* @author Jeet Prakash
* */
export type Response = {
  status: boolean,
  message: string,
  data: Test | Item[] | Token
};

export type Item = {
  name: string,
  directory: boolean,
  isRoot: boolean,
  rootAlias: string,
  path: string
};

export type Test = {
  message: string
}

export type Token = {
  token: string
}